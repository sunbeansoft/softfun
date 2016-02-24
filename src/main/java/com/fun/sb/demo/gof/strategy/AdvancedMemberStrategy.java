package com.fun.sb.demo.gof.strategy;

public class AdvancedMemberStrategy implements MemberStrategy {

    public double calcPrice(double booksPrice) {
        
        System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
    }
}