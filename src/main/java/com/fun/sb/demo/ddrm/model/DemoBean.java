package com.fun.sb.demo.ddrm.model;

import com.fun.sb.demo.ddrm.annotation.DataResource;
import org.springframework.stereotype.Service;

/**
 * Created by sunbeansoft on 15-8-19.
 */

@Service
public class DemoBean extends AbstractDDRMBean {
    @DataResource
    private static String a;

    private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
        System.out.println("set New Value " + a);
    }

    public static void main(String[] args) {
        new DemoBean();
    }

}
