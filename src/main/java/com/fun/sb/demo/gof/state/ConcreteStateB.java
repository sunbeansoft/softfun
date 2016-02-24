package com.fun.sb.demo.gof.state;

public class ConcreteStateB implements State {

    public void handle(String sampleParameter) {

        System.out.println("ConcreteStateB handle ：" + sampleParameter);
    }

}