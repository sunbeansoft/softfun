package com.fun.sb.demo.ddrm;

import com.alibaba.fastjson.JSON;
import com.fun.sb.demo.ddrm.annotation.DataResource;
import com.fun.sb.demo.ddrm.model.DDRMResult;
import com.fun.sb.demo.ddrm.model.FieldResult;
import com.google.common.collect.Maps;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by sunbeansoft on 15-8-19.
 */
public class DistributeDataResourceManager {


    /**
     * 当前服务域名称
     */
    private String domain;

    /**
     * 数据客户端
     */
    private DDRMAgent ddrmAgent;

    /**
     * 本地缓存
     */
    private Map<String, Object> resourceMap = Maps.newHashMap();


    public void regist(String domain, Object targetInstance) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        this.domain = domain;
        Class targetClass = targetInstance.getClass();
        if (!hasDataResource(targetClass.getDeclaredFields())) {
            return;
        }
        String className = targetClass.getName();
        resourceMap.put(domain + ":" + className, targetInstance);
//        DDRMResult result = ddrmAgent.fetchDDRMData(domain, targetClass.getName());
//        if (result != null && result.isSuccess()) {
//            insertValue(targetClass, targetInstance, result.getResult());
//        } else {
//            logger.error("返回结果异常" + result.getMsg());
//        }
    }

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

    public void setNewValue(DDRMResult result) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Object targetInstance = resourceMap.get(result.getDomain() + ":" + result.getClassFullName());
        if (targetInstance == null) {
            return;
        }
        Class targetClass = targetInstance.getClass();
        if (result != null && result.isSuccess()) {
            insertValue(targetClass, targetInstance, result.getResult());
        } else {
            //log
        }
    }

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

    public DDRMAgent getDdrmAgent() {
        return ddrmAgent;
    }

    public void setDdrmAgent(DDRMAgent ddrmAgent) {
        this.ddrmAgent = ddrmAgent;
    }
}
