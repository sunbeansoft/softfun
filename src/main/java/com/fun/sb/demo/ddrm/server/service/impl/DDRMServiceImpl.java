package com.fun.sb.demo.ddrm.server.service.impl;


import com.fun.sb.demo.ddrm.model.DDRMRequest;
import com.fun.sb.demo.ddrm.model.DDRMResult;
import com.fun.sb.demo.ddrm.model.DDRMServiceResult;
import com.fun.sb.demo.ddrm.model.FieldResult;
import com.fun.sb.demo.ddrm.server.GlobalSession;
import com.fun.sb.demo.ddrm.server.service.DDRMService;
import io.netty.channel.Channel;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunbeansoft on 15-8-30.
 */
public class DDRMServiceImpl implements DDRMService {


    @Override
    public DDRMResult queryDomainProperties(String domain) {
        DDRMResult message = new DDRMResult();
        message.setSuccess(true);
        message.setClassFullName("com.fun.sb.demo.ddrm.model");
        message.setDomain("crm");
        List<FieldResult> fieldResults = new ArrayList<FieldResult>();
        FieldResult fieldResult = new FieldResult();
        fieldResult.setName("a");
        fieldResult.setValue("Hello world!");
        fieldResults.add(fieldResult);
        message.setResult(fieldResults);
        return message;
    }

    @Override
    public DDRMServiceResult operateClientRequest(Object clientRequest, Channel channel) {
        DDRMServiceResult result = new DDRMServiceResult(false, true);
        try {
            if (clientRequest instanceof DDRMRequest) {
                operateDomainProperties((DDRMRequest) clientRequest, channel, result);
            }
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * 处理获取参数请求
     *
     * @param request client请求
     * @param channel 连接
     * @param result  返回结果
     */
    private void operateDomainProperties(DDRMRequest request, Channel channel, DDRMServiceResult result) {
        GlobalSession.addDomainMap(request.getDomain(), channel);
        DDRMResult message = queryDomainProperties(request.getDomain());
        result.setResult(message);
        result.setSuccess(true);
    }

    @Override
    public DDRMServiceResult dropChannel(Channel channel) {
        DDRMServiceResult result = new DDRMServiceResult(false, false);
        boolean success = GlobalSession.dropChannel(channel);
        result.setSuccess(success);
        return result;
    }

    @Override
    public DDRMServiceResult pushPropertiesToDomain(String domain, DDRMResult properties) {
        DDRMServiceResult result = new DDRMServiceResult(false, false);
        List<Channel> channels = GlobalSession.getDomainContext(domain);
        if (CollectionUtils.isEmpty(channels)) {
            result.setMsg("没有" + domain + "所对应的数据");
            return result;
        }
        for (Channel channel : channels) {
            channel.writeAndFlush(properties);
        }
        result.setSuccess(true);
        return result;
    }
}
