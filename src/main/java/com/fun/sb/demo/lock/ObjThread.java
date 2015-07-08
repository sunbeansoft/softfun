package com.fun.sb.demo.lock;

public class ObjThread extends Thread {
    LockTestClass lock;
    int i = 0;

    public ObjThread(LockTestClass lock, int i) {
        this.lock = lock;
        this.i = i;
    }

    public void run() {
        //��������
//		lock.noSynMethod(this.getId(),this);
        //����������1������synchronized synInMethod�ķ�ʽ
        lock.synInMethod();
        //����������2������synchronized(this)�ķ�ʽ
		lock.synOnMethod();
        //˽��������������synchronized(object)�ķ�ʽ
//		lock.synMethodWithObj();
        //��������������static synchronized increment�ķ�ʽ
        LockTestClass.increament();
    }
}