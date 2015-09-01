package com.fun.sb.demo.ddrm.model;

import com.fun.sb.demo.ddrm.DistributeDataResourceManager;
import com.fun.sb.demo.ddrm.annotation.DataResource;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by sunbeansoft on 15-8-19.
 */
public class DemoBean {
    @DataResource
    private static String a;

    public DemoBean() {
        DistributeDataResourceManager manager = new DistributeDataResourceManager();
        try {
            manager.regist("crm", this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
