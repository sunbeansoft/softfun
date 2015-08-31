package com.fun.sb.demo.ddrm.server.service;


import com.fun.sb.demo.ddrm.model.DDRMResult;

/**
 * Created by sunbeansoft on 15-8-30.
 */
public interface DDRMService {

    /**
     * 获取相关属性
     *
     * @param domain
     * @return
     */
    public DDRMResult queryDomainProperties(String domain);
}
