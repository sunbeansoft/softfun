package com.fun.sb.demo.ddrm.agent.impl;

import com.alibaba.fastjson.JSONObject;
import com.fun.sb.demo.ddrm.DDRMAgent;
import com.fun.sb.demo.ddrm.model.DDRMResult;
import com.fun.sb.demo.ddrm.model.FieldResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by sunbeansoft on 15-8-19.
 */
public class DDRMAgentImpl implements DDRMAgent {

    public DDRMResult fetchDDRMData(String domain, String classFullName) {
        DDRMResult ddrmResult = new DDRMResult(false);
        try {
            Map<String, Object> resultMap = Maps.newHashMap();
            resultMap.put("key", "hello world");
            JSONObject obj = new JSONObject();
            obj.putAll(resultMap);
            List<FieldResult> results = Lists.newArrayList();
            FieldResult result = ddrmResult.newFieldResult();
            result.setName("key");
            result.setValue("hello world");
            results.add(result);
            ddrmResult.setResult(results);
            ddrmResult.setSuccess(true);
        } catch (Exception e) {
            ddrmResult.setMsg("服务异常");
        } finally {
            return ddrmResult;
        }
    }

    public boolean putDDRMData(String domain, String classFullName, String jsonData) {
        DDRMResult ddrmResult = new DDRMResult(false);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("key", "hello world");
        JSONObject obj = new JSONObject();
        obj.putAll(resultMap);
        List<FieldResult> results = Lists.newArrayList();
        FieldResult result = ddrmResult.newFieldResult();
        result.setName("key");
        result.setValue("hello world");
        results.add(result);
        ddrmResult.setResult(results);
        ddrmResult.setSuccess(true);
//        try {
////            nonblockingWriter.mainloop(JSON.json(ddrmResult));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return false;
    }

}
