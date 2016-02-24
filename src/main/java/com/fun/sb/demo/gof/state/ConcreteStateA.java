package com.fun.sb.demo.gof.state;

public class ConcreteStateA implements State {

    public void handle(String sampleParameter) {
        
        System.out.println("ConcreteStateA handle ：" + sampleParameter);
    }

}