package com.fun.sb.demo.ddrm;

import com.alibaba.fastjson.JSON;
import com.fun.sb.demo.ddrm.annotation.DataResource;
import com.fun.sb.demo.ddrm.client.DDRMClient;
import com.fun.sb.demo.ddrm.model.DDRMRequest;
import com.fun.sb.demo.ddrm.model.DDRMResult;
import com.fun.sb.demo.ddrm.model.FieldResult;
import com.fun.sb.demo.ddrm.server.DDRMServer;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sunbeansoft on 15-8-19.
 */
public class DistributeDataResourceManager {

    /**
     * 调用server的管道
     */
    private Channel channel;

    /**
     * 本地缓存
     */
    private static Map<String, Object> resourceMap = Maps.newHashMap();

    private ReentrantLock reqLock = new ReentrantLock();
    private Condition reqCondition = reqLock.newCondition();

    //链接服务端线程
    private Thread ddrmClient = new Thread(new DDRMClient("127.0.0.1", 11000, this));

    public DistributeDataResourceManager() {
        ddrmClient.start();
    }


    /**
     * 注册新资源
     *
     * @param domain         域
     * @param targetInstance 域实例
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public void regist(String domain, Object targetInstance) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InterruptedException {
        Class targetClass = targetInstance.getClass();
        if (!hasDataResource(targetClass.getDeclaredFields())) {
            return;
        }
        String className = targetClass.getName();
        String newResourceName = domain + ":" + className;
        if (!resourceMap.containsKey(newResourceName)) {
            try {
                reqLock.lock();
                resourceMap.put(domain + ":" + className, targetInstance);
                DDRMRequest request = new DDRMRequest();
                request.setDomain(domain);
                //如果无法链接到server的话等待
                if (channel == null) {
                    //等待时间不超过5分钟，防止不能启动
                    reqCondition.await(1000 * 1000 * 60 * 5l, TimeUnit.MICROSECONDS);
                }
                if (channel != null) {
                    //触发请求
                    channel.writeAndFlush(request);
                    System.out.println("send request:" + request);
                }
            } finally {
                reqLock.unlock();
            }
        }
    }

    /**
     * 反射值
     *
     * @param targetClass    目标类型
     * @param targetInstance 目标实例
     * @param result         反射的值
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void insertValue(Class targetClass, Object targetInstance, List<FieldResult> result) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (FieldResult fieldResult : result) {
            String fieldName = fieldResult.getName();
            Field field = targetClass.getDeclaredField(fieldResult.getName());
            if (field == null)
                continue;
            Class<?> fieldType = field.getType();
            String firstLeter = fieldName.substring(0, 1);
            fieldName = fieldName.replace(firstLeter, firstLeter.toUpperCase());
            Method method = targetClass.getMethod("set" + fieldName, fieldType);
            method.invoke(targetInstance, fieldResult.getValue());
        }
    }

    public void setNewValue(String resultJson) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        DDRMResult result = JSON.parseObject(resultJson, DDRMResult.class);
        setNewValue(result);
    }

    /**
     * 射入的值
     *
     * @param serverProperties server端的数据
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public void setNewValue(DDRMResult serverProperties) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Object targetInstance = resourceMap.get(serverProperties.getDomain() + ":" + serverProperties.getClassFullName());
        if (targetInstance == null) {
            return;
        }
        Class targetClass = targetInstance.getClass();
        if (serverProperties != null && serverProperties.isSuccess()) {
            insertValue(targetClass, targetInstance, serverProperties.getResult());
        } else {
            //log
        }
    }

    /**
     * 是否有值
     *
     * @param targetFields
     * @return
     */
    private boolean hasDataResource(Field[] targetFields) {
        for (Field field : targetFields) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof DataResource) {
                    return true;
                }
            }
        }
        return false;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        try {
            reqLock.lock();
            this.channel = channel;
            reqCondition.signal();
        } finally {
            reqLock.unlock();
        }
    }
}
