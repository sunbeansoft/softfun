package com.fun.sb.demo.ddrm.model;

import java.io.Serializable;

public class FieldResult implements Serializable {
    /**
     * 字段名称
     */
    private String name;
    /**
     * 字段值
     */
    private String value;

    public FieldResult() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}