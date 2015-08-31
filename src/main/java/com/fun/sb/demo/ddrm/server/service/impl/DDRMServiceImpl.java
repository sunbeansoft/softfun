package com.fun.sb.demo.ddrm.server.service.impl;

import com.baidu.nuomi.crm.ddrm.FieldResult;
import com.baidu.nuomi.crm.ddrm.model.DDRMResult;
import com.baidu.nuomi.crm.ddrm.server.service.DDRMService;

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
        message.setClassFullName("com.baidu.nuomi.crm.ddrm.DemoBean");
        message.setDomain("crm");
        List<FieldResult> fieldResults = new ArrayList<FieldResult>();
        FieldResult fieldResult = new FieldResult();
        fieldResult.setName("a");
        fieldResult.setValue("Hello world!");
        fieldResults.add(fieldResult);
        message.setResult(fieldResults);
        return message;
    }
}
