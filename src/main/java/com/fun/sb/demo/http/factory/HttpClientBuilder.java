package com.fun.sb.demo.http.factory;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * httpclient创建工厂类
 * User: shangbin01
 * Date: 16/4/25
 * Time: 下午9:12
 */
public class HttpClientBuilder {

    /**
     * 最终执行方法
     */
    private static CloseableHttpClient concurrentClient;

    /**
     * 线程安全请求客户端
     *
     * @return
     */
    public static CloseableHttpClient concurrentClient() {
        if (concurrentClient == null) {
            synchronized (HttpClientBuilder.class) {
                if (concurrentClient == null) {
                    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
                    cm.setMaxTotal(20);//最多20个线程同时请求
                    cm.setDefaultMaxPerRoute(10);//一个链接最多10个线程同时请求
                    concurrentClient = HttpClients.custom()
                            .setConnectionManager(cm)
                            .build();
                }
            }
        }
        return concurrentClient;
    }

    /**
     * 单线程客户端
     *
     * @return
     */
    public static CloseableHttpClient newClient() {
        return HttpClients.custom().build();
    }
}
