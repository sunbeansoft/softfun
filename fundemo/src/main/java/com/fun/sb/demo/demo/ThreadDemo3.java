package com.fun.sb.demo.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一个版本的生产者和消费者线程
 *
 * @author pinefantasy
 * @since 2013-11-1
 */
public class ThreadDemo3 {
    /**
     * 姑且卖车的当做是生产者线程
     */
    public static class CarSeller implements Runnable {
        private CarBigHouse bigHouse;

        public CarSeller(CarBigHouse bigHouse) {
            this.bigHouse = bigHouse;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {// 当做生产者线程，往仓库里边增加汽车，其实是触发增加汽车
                int count = bigHouse.put();
                System.out.println("生产汽车-->count = " + count);
            }
        }
    }

    /**
     * 姑且买车的人当做是消费者线程
     */
    public static class Consumer implements Runnable {
        private CarBigHouse bigHouse;

        public Consumer(CarBigHouse bigHouse) {
            this.bigHouse = bigHouse;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {// 当做消费者线程，从仓库里边提取汽车，其实是触发，从仓库里边提取一辆汽车出来
                int count = bigHouse.get();
                System.out.println("消费汽车-->count = " + count);
            }
        }
    }

    /**
     * 这边姑且当做是车子big house放车子的仓库房
     */
    public static class CarBigHouse {
        public int carNums = 0;// 这边是仓库房子中车子的数量总数
        public List<Car> carList = new ArrayList<Car>();// 这边模拟用来放汽车的list 

        public int put() {// 提供给生产者放汽车到仓库的接口
            Car car = CarFactory.makeNewCar();
            carList.add(car);// 加到仓库中去
            carNums++;// 总数增加1
            return carNums;
        }

        public int get() {// 提供给消费者从这边取汽车接口
            Car car = null;
            if (carList.size() != 0) {// size不为空才去取车
                car = carList.get(carList.size() - 1);// 提取最后一个car
                carList.remove(car);// 从从库list中移除掉
                carNums--;// 总数减少1
            }
            return carNums;
        }
    }

    public static class Car {
        public String carName;// 汽车名称
        public double carPrice;// 汽车价格

        public Car() {
        }

        public Car(String carName, double carPrice) {
            this.carName = carName;
            this.carPrice = carPrice;
        }
    }

    /**
     * 采用静态工厂方式创建car对象，这个只是简单模拟，不做设计模式上的过多考究
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
     * 第一个版本的生产者和消费者线程，没有加上同步机制的演示例子
     *
     * @param args
     */
    public static void main(String[] args) {
        CarBigHouse bigHouse = new CarBigHouse();
        new Thread(new CarSeller(bigHouse)).start();
        new Thread(new Consumer(bigHouse)).start();
    }
}
 