package com.fun.sb.demo.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ���̶߳�ͬһ���������в���
 *
 * @author pinefantasy
 * @since 2013-10-31
 */
public class ThreadDemo2 {
    private static int count = 0;

    public static class CountThread implements Runnable {// 1.������̰߳�ȫ���⣬�������������

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.println(Thread.currentThread().getName() + ", count = " + count);
            }
        }
    }

    private static final Object lock = new Object();// ���ʹ�õ�lock����

    public static class Count2Thread implements Runnable {// ���ʹ�õ��ǻ�������ʽ

        @Override
        public void run() {
            synchronized (lock) {// ʹ�û�������ʽ����
                for (int i = 0; i < 100; i++) {
                    count++;
                    System.out.println(Thread.currentThread().getName() + ", count = " + count);
                }
            }
        }
    }

    private static AtomicInteger ai = new AtomicInteger();// ���ʹ�õ��ǲ�������AtomicXXX�࣬ʹ�õ���CAS��ʽ��compare and swap

    public static class Count3Thread implements Runnable {// AtomicInteger�ڲ���CASʵ�ַ�ʽ�����õ��ǣ�ѭ�����жϡ�������������ʽ

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                int tmp = ai.incrementAndGet();// ����CAS��ʽ����
                System.out.println(Thread.currentThread().getName() + ", count = " + tmp);
            }
        }
    }

    private static volatile int countV = 0;// �����volatile���ö��̸߳�֪����Ϊֵ�Ƿ���������

    public static class Count4Thread implements Runnable {// volatile����ı���ֻ��˵�ŵ������棬��ʱ++����������ԭ�Ӳ��������ҪС��

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(50);// ������߳������£����ӳ������
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countV++;// volatileҪ��ȷʹ�ã�����˵�����volatile���ǰ�ȫ�ģ�����Ҫע��++ --����������ԭ�Ӳ���
                System.out.println(Thread.currentThread().getName() + ", count = " + countV);
            }
        }
    }

    /**
     * ʹ�÷��ͼ򵥱�дһ�����Է���
     *
     * @param <T>
     * @param t
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InterruptedException
     */
    public static <T> void testTemplate(T t) throws InstantiationException, IllegalAccessException, InterruptedException {
        for (int i = 0; i < 5; i++) {
            if (t instanceof Runnable) {
                Class<?> c = t.getClass();
                Object object = c.newInstance();
                new Thread((Runnable) object).start();
            }
        }
    }

    /**
     * <pre>
     * 1.test1 �̲߳���ȫ��ʾ���ӣ�count�������ܵõ�Ԥ�ڵ�Ч��
     * 2.test2 ��test1�����ϸĽ��ģ��û�����sync����
     * 3.test3 ��test1�����ϸĽ��ģ���AtomicInteger����ʵ��
     * 4.test4 ������ķ�������Ϊi++������ԭ�Ӳ�������count����Ϊvolatile���͵�
     *
     * @param args
     * @throws InterruptedException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException {
        // 1.����1
        // testTemplate(new CountThread());
        // 2.����2
        // testTemplate(new Count2Thread());
        // 3.����3
        // testTemplate(new Count3Thread());
        // 4.����4
        testTemplate(new Count4Thread());
        Thread.sleep(15000);
        System.out.println(count);
        System.out.println(ai.get());
        System.out.println(countV);
    }
}
 