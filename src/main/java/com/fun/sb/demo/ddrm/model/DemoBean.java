package com.fun.sb.demo.ddrm.model;

import com.fun.sb.demo.ddrm.annotation.DataResource;

/**
 * Created by sunbeansoft on 15-8-19.
 */
public class DemoBean {
    @DataResource
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
        System.out.println("set New Value " + a);
    }
}
