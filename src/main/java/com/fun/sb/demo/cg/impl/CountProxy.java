package com.fun.sb.demo.cg.impl;

import com.fun.sb.demo.cg.Count;

/**
 * ����һ�������ࣨ��ǿCountImplʵ���ࣩ
 *
 * @author Administrator
 */
public class CountProxy implements Count {
    private CountImpl countImpl;

    /**
     * ����Ĭ�Ϲ�����
     *
     * @param countImpl
     */
    public CountProxy(CountImpl countImpl) {
        this.countImpl = countImpl;
    }

    public void queryCount() {
        System.out.println("������֮ǰ");
        // ����ί����ķ���;  
        countImpl.queryCount();
        System.out.println("������֮��");
    }

    public void updateCount() {
        System.out.println("������֮ǰ");
        // ����ί����ķ���;  
        countImpl.updateCount();
        System.out.println("������֮��");

    }

}  