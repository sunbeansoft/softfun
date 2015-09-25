package com.baidu.nuomi.crm.ddrm.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sunbeansoft on 15-8-30.
 */
public class DDRMRequest implements Serializable {

    /**
     * 域
     */
    private String domain;
    /**
     * ip
     */
    private String ip;
    /**
     * 额外参数
     */
    private Map<String, String> extraReq;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Map<String, String> getExtraReq() {
        return extraReq;
    }

    public void setExtraReq(Map<String, String> extraReq) {
        this.extraReq = extraReq;
    }
}
