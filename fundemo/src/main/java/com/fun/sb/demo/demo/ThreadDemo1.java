package com.fun.sb.demo.demo;

/**
 * 线程简单演示例子程序
 *
 * @author pinefantasy
 * @since 2013-10-31
 */
public class ThreadDemo1 {
    /**
     * 第一种方式：继承自Thread类，覆写run方法
     */
    public static class Test1Thread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Test1," + Thread.currentThread().getName() + ", i = " + i);
            }
        }
    }

    /**
     * 第二种方式：实现Runnable接口，实现run方法
     */
    public static class Test2Thread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Test2," + Thread.currentThread().getName() + ", i = " + i);
            }
        }
    }

    /**
     * <pre>
     *
     * 主线程为main线程
     * 分支线程为：1 2 3 三种简单实现方式
     *
     * @param args
     */
    public static void main(String[] args) {
        new Test1Thread().start();// 启动线程1
        new Thread(new Test2Thread()).start();// 启动线程2
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Test3," + Thread.currentThread().getName() + ", i = " + i);
                }
            }
        }).start();// 启动线程3
    }
}
 