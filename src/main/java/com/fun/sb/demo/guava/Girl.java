package com.fun.sb.demo.guava;

import com.google.common.collect.ComparisonChain;
import org.junit.Test;

class Girl implements Comparable<Girl> {

    private String name;//名称
    private double height;//身高
    private String face;//长相

    Girl(String name, double height, String face) {
        this.name = name;
        this.height = height;
        this.face = face;
    }

    //传统方法我们这样比较
    @Test
    public int compareTo(Girl girl) {
        int c1 = name.compareTo(girl.name);
        if (c1 != 0) {
            System.out.println("两个girl的name不相同");
            return c1;
        }
        int c2 = Double.compare(height, girl.height);
        if (c2 != 0) {
            System.out.println("两个girl的height不相同");
            return c2;
        }
        int c3 = face.compareTo(girl.face);
        if (c3 != 0)
            System.out.println("两个girl的face不相同");
        return c3;
    }

    @Test
    public void testCompareTo() {
        Girl g1 = new Girl("lisa", 175.00, "nice");
        Girl g2 = new Girl("lisa", 175.00, "beauty");
        //两个girl的face不相同
        System.out.println(g1.compareTo(g2) == 0);//false
    }

    @Test
    public int compareTo1(Girl girl) {
        return ComparisonChain.start()
                .compare(name, girl.name)
                .compare(height, girl.height)
                .compare(face, girl.face)
                .result();
    }
}