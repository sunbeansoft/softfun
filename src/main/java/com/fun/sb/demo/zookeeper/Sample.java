package com.fun.sb.demo.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * Created by shangbin01 on 2016/1/12.
 */
public class Sample {

    public Sample() {
        ZkClient zkClient = new ZkClient("10.94.34.33:8787", 30000);
//        zkClient.create("/ddrm","", CreateMode.EPHEMERAL);
        zkClient.subscribeChildChanges("/ddrm-root", new IZkChildListener() {
            public void handleChildChange(String parentPath, List<String> children) throws Exception {
                if (children == null) {
                    System.out.println("<" + parentPath + "> is deleted");
                    return;
                }
                for (String child : children) {
                    System.out.println("<Child>:" + child);
                }
            }
        });
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public static void main(String[] args) {
        new Sample();
    }
}
