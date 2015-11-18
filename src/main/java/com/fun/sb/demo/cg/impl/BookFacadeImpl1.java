package com.fun.sb.demo.cg.impl;

import java.io.Serializable;

/**
 * 这个是没有实现接口的实现类
 *
 * @author student
 */
public class BookFacadeImpl1 implements Serializable {
    public void addBook() {
        System.out.println("增加图书的普通方法...");
    }

    public void aaa() {
        System.out.println("test");
    }
}  