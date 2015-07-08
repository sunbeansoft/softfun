package com.fun.sb.demo.lock;

public class LockTestClass {
    //������������
    private static int i = 0;
    //˽����
    private Object object = new Object();

    /**
     * &lt;p&gt;
     * ��������
     *
     * @param threadID
     * @param thread
     */
    public void noSynMethod(long threadID, ObjThread thread) {
        System.out.println("nosyn: class obj is " + thread + ", threadId is"
                + threadID);
    }

    /**
     * ����������1
     */
    public synchronized void synOnMethod() {
        System.out.println("synOnMethod begins" + ", time = "
                + System.currentTimeMillis() + "ms");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synOnMethod ends");
    }

    /**
     * ����������2,����synchronized (this)������
     */
    public void synInMethod() {
        synchronized (this) {
            System.out.println("synInMethod begins" + ", time = "
                    + System.currentTimeMillis() + "ms");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synInMethod ends");
        }

    }

    /**
     * ����������3
     */
    public void synMethodWithObj() {
        synchronized (object) {
            System.out.println("synMethodWithObj begins" + ", time = "
                    + System.currentTimeMillis() + "ms");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synMethodWithObj ends");
        }
    }

    /**
     * ����
     */
    public static synchronized void increament() {
        System.out.println("class synchronized. i = " + i + ", time = "
                + System.currentTimeMillis() + "ms");
        i++;
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("class synchronized ends.");
    }

}