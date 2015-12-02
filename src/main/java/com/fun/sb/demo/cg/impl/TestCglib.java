package com.fun.sb.demo.cg.impl;

import net.sf.cglib.core.DebuggingClassWriter;

public class TestCglib {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\");
        BookFacadeCglib cglib = new BookFacadeCglib();
        BookFacadeImpl1 bookCglib = (BookFacadeImpl1) cglib.getInstance(new BookFacadeImpl1());
        System.out.println(bookCglib);
        bookCglib.addBook();
        bookCglib.aaa();
    }
}  