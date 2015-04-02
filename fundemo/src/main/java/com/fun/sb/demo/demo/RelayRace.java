package com.fun.sb.demo.demo;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ��javaģ��4X100������
 * �������� ���ɼ���Ӧ��CyclicBarrier
 *
 * @author Сe
 *         <p/>
 *         2010-4-30 ����08:13:40
 */
class Player implements Runnable {
    private String name;
    private CyclicBarrier barrier;
    private Player next;//��һ��
    private int time;//��ʱ
    private boolean run;//��һ��

    public Player(String name, CyclicBarrier barrier, boolean run) {
        super();
        this.name = name;
        this.barrier = barrier;
        this.run = run;
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!run) {//�ȴ���Ա
                    wait();
                }
            }
            Random r = new Random();
            TimeUnit.MILLISECONDS.sleep(r.nextInt(2000));
            next(next, 11 + r.nextInt(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void next(Player next, int time) {
        System.out.println(name + " ��ʱ��" + time + ",���Ӱ�");
        if (next != null) {
            next.setTime(this.time + time);
            synchronized (next) {
                next.setRun(true);
                next.notify();
            }
        } else {
            System.out.println("���꣬����ʱ��" + (this.time + time));
        }
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    public void setNext(Player next) {
        this.next = next;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

}

public class RelayRace {

    public static void main(String[] args) throws InterruptedException {
        final Player[] players = new Player[4];
        ExecutorService exec = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {

            @Override
            public void run() {
                System.out.println("����������ʱ��" + players[3].getTime());
            }
        });
        for (int i = 0; i < 4; i++) {
            players[i] = new Player("��Ա" + (i + 1), barrier, i == 0);
        }
        for (int i = 0; i < 4; i++) {
            if (i < 3) {
                players[i].setNext(players[i + 1]);
                exec.execute(players[i]);
            } else {
                exec.execute(players[3]);
                break;
            }
        }
        /*TimeUnit.SECONDS.sleep(3);
		 * CyclicBarrier ��������
		for(int i = 0; i < 4; i++){
			if( i < 3){
				players[i].setNext(players[i + 1]);
				exec.execute(players[i]);
			}else{
				exec.execute(players[3]);
				break;
			}
		}*/
    }

}
