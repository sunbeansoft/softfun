package com.fun.sb.demo.http.fetcher;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * 资源获取器 可以获取本地资源,也可以获取远程资源
 * User: shangbin01
 * Date: 16/4/25
 * Time: 下午4:53
 */
public interface ResourceFetcher {

    /**
     * 通过url获取资源 url可以为http连接,也可以为本地地址
     *
     * @param url 请求地址
     * @return
     */
    public HttpRequestBase newRequest(String url);

}
