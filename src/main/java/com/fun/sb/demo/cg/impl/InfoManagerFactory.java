package com.fun.sb.demo.cg.impl;

import net.sf.cglib.proxy.Enhancer;

public class InfoManagerFactory {
    private static InfoManager manger = new InfoManager();

    /**
     * ����ԭʼ��InfoManager
     *
     * @return
     */
    public static InfoManager getInstance() {
        return manger;
    }

    /**
     * ��������Ȩ�޼����InfoManager
     *
     * @param auth
     * @return
     */
    public static InfoManager getAuthInstance(AuthProxy auth) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(InfoManager.class);
        enhancer.setCallback(auth);
        return (InfoManager) enhancer.create();
    }
}