package com.fun.sb.demo.ddrm.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunbeansoft on 15-8-19.
 */
public final class DDRMResult implements Serializable {

    public DDRMResult() {
    }

    public DDRMResult(boolean success) {
        this.success = success;
    }

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 域名称
     */
    private String domain;

    /**
     * 类全名称
     */
    private String classFullName;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private List<FieldResult> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<FieldResult> getResult() {
        return result;
    }

    public void setResult(List<FieldResult> result) {
        this.result = result;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public FieldResult newFieldResult() {
        return new FieldResult();
    }

}
