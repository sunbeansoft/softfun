package com.fun.sb.demo.cg.impl;


/**
 * ≤‚ ‘Count¿‡
 *
 * @author Administrator
 */
public class TestCount {
    public static void main(String[] args) {
        CountImpl countImpl = new CountImpl();
        CountProxy countProxy = new CountProxy(countImpl);
        countProxy.updateCount();
        countProxy.queryCount();

    }
}  