package com.fun.sb.demo.demo;

/**
 * �̼߳���ʾ���ӳ���
 *
 * @author pinefantasy
 * @since 2013-10-31
 */
public class ThreadDemo1 {
    /**
     * ��һ�ַ�ʽ���̳���Thread�࣬��дrun����
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
     * �ڶ��ַ�ʽ��ʵ��Runnable�ӿڣ�ʵ��run����
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
     * ���߳�Ϊmain�߳�
     * ��֧�߳�Ϊ��1 2 3 ���ּ�ʵ�ַ�ʽ
     *
     * @param args
     */
    public static void main(String[] args) {
        new Test1Thread().start();// �����߳�1
        new Thread(new Test2Thread()).start();// �����߳�2
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Test3," + Thread.currentThread().getName() + ", i = " + i);
                }
            }
        }).start();// �����߳�3
    }
}
 