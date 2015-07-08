package com.fun.sb.demo.demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ģ����Ŀ�Ŀ�����ֻ�е�ÿ��ģ�鶼��ɺ���Ŀ�����
 * ÿ��ģ�����ʱ��ͬ
 *
 * @author Сe
 *         <p/>
 *         2010-4-30 ����07:41:37
 */
class Module implements Runnable {
    private CountDownLatch latch;
    private String moduleName;
    private int time;//��ʱ  


    public Module(CountDownLatch latch, String moduleName, int time) {
        super();
        this.latch = latch;
        this.moduleName = moduleName;
        this.time = time;
    }


    @Override
    public void run() {
        try {
            work();
            latch.countDown();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }

    }

    private void work() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(time);
        System.out.println(moduleName + " ��ɣ���ʱ:" + time);
    }
}

class Controller implements Runnable {
    private CountDownLatch latch;

    public Controller(CountDownLatch latch) {
        super();
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("����ģ�鶼��ɣ��������");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }

    }

}

public class Project {
    static final int SIZE = 20;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(SIZE);
        Random r = new Random();
        ExecutorService exec = Executors.newCachedThreadPool();
        Controller controller = new Controller(latch);
        exec.execute(controller);
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new Module(latch, "ģ��" + (i + 1), r.nextInt(2000)));
        }

        exec.shutdown();

    }

}  