package com.fun.sb.demo.gof.template;

public class Student extends AbstractPerson {
    @Override
    protected void dressUp() {
        System.out.println("穿校服");
    }

    @Override
    protected void eatBreakfast() {
        System.out.println("吃妈妈做好的早饭");
    }

    @Override
    protected void takeThings() {
        System.out.println("public class Student extends AbstractPerson{\n" +
                "     @Override\n" +
                "     protected void dressUp() {\n" +
                "          System.out.println(“穿校服\");\n" +
                "     }\n" +
                "     @Override\n" +
                "     protected void eatBreakfast() {\n" +
                "          System.out.println(“吃妈妈做好的早饭\");\n" +
                "     }\n" +
                "\n" +
                "     @Override\n" +
                "     protected void takeThings() {\n" +
                "          System.out.println(“背书包，带上家庭作业和红领巾\");\n" +
                "     }\n" +
                "}背书包，带上家庭作业和红领巾");
    }
}