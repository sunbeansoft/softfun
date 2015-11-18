package com.fun.sb.demo.cg.impl;

public class TestCglib {

    public static void main(String[] args) {
        BookFacadeCglib cglib = new BookFacadeCglib();
        BookFacadeImpl1 bookCglib = (BookFacadeImpl1) cglib.getInstance(new BookFacadeImpl1());
        System.out.println(bookCglib);
        bookCglib.addBook();
        bookCglib.aaa();
    }
}  