package com.fun.sb.demo.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * �ڶ����汾���������������߳�
 *
 * @author pinefantasy
 * @since 2013-11-1
 */
public class ThreadDemo5 {
    /**
     * ���������ĵ������������߳�
     */
    public static class CarSeller implements Runnable {
        private CarBigHouse bigHouse;

        public CarSeller(CarBigHouse bigHouse) {
            this.bigHouse = bigHouse;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {// �����������̣߳����ֿ����������������ʵ�Ǵ�����������
                int count = bigHouse.put();
                System.out.println("��������-->count = " + count);
            }
        }
    }

    /**
     * �����򳵵��˵������������߳�
     */
    public static class Consumer implements Runnable {
        private CarBigHouse bigHouse;

        public Consumer(CarBigHouse bigHouse) {
            this.bigHouse = bigHouse;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {// �����������̣߳��Ӳֿ������ȡ��������ʵ�Ǵ������Ӳֿ������ȡһ����������
                int count = bigHouse.get();
                System.out.println("��������-->count = " + count);
            }
        }
    }

    /**
     * ��߹��ҵ����ǳ���big house�ų��ӵĲֿⷿ
     */
    public static class CarBigHouse {
        public int carNums = 0;// ����ǲֿⷿ���г��ӵ���������
        public List<Car> carList = new ArrayList<Car>();// ���ģ��������������list
        public static final int max = 100;// �������£������������� 
        private Object lock = new Object();// ����object��wait��notify��ʽ����ͬ������

        public int put() {// �ṩ�������߷��������ֿ�Ľӿ�
            synchronized (lock) {
                if (carList.size() == max) {// �ﵽ�����ޣ���������car
                    try {
                        lock.wait();// ������������
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Car car = CarFactory.makeNewCar();
                carList.add(car);// �ӵ��ֿ���ȥ
                carNums++;// ��������1
                lock.notify();// ���ѵȴ����߳�
                return carNums;
            }
        }

        public int get() {// �ṩ�������ߴ����ȡ�����ӿ�
            Car car = null;
            synchronized (lock) {
                if (carList.size() == 0) {// û������������������
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (carList.size() != 0) {// size��Ϊ�ղ�ȥȡ��
                    car = carList.get(carList.size() - 1);// ��ȡ���һ��car
                    carList.remove(car);// �Ӵӿ�list���Ƴ���
                    carNums--;// ��������1
                }
                lock.notify();
                return carNums;
            }
        }
    }

    public static class Car {
        public String carName;// ��������
        public double carPrice;// �����۸�

        public Car() {
        }

        public Car(String carName, double carPrice) {
            this.carName = carName;
            this.carPrice = carPrice;
        }
    }

    /**
     * ���þ�̬������ʽ����car�������ֻ�Ǽ�ģ�⣬�������ģʽ�ϵĹ��࿼��
     */
    public static class CarFactory {
        private CarFactory() {
        }

        public static Car makeNewCar(String carName, double carPrice) {
            return new Car(carName, carPrice);
        }

        public static Car makeNewCar() {
            return new Car();
        }
    }

    /**
     * �ڶ����汾�������ߺ��������̣߳�������ͬ�����Ƶķ���
     *
     * @param args
     */
    public static void main(String[] args) {
        CarBigHouse bigHouse = new CarBigHouse();
        new Thread(new CarSeller(bigHouse)).start();
        new Thread(new Consumer(bigHouse)).start();
    }
}
 