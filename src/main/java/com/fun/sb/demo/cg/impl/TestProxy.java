package com.fun.sb.demo.cg.impl;

import com.fun.sb.demo.cg.BookFacade;

public class TestProxy {
  
    public static void main(String[] args) {  
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookProxy.addBook();  
    }  
  
}  