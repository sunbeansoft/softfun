package com.fun.sb.demo.http.fetcher;

import com.fun.sb.demo.http.factory.HttpClientBuilder;
import com.fun.sb.demo.http.parser.ResultParser;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 通过Get方式获取远程数据
 * User: shangbin01
 * Date: 16/4/26
 * Time: 上午12:15
 */
public class GetResourceFetcher extends RemoteResourceFetcher {

    /**
     * 通过Get方式获取远程数据
     *
     * @param remoteUrl    远程地址
     * @param resultParser 结果解析器
     * @param <T>          结果类型
     * @return
     */
    public <T> T getResource(String remoteUrl, ResultParser<T> resultParser) {
        HttpRequestBase get = newRequest(remoteUrl);
        return fetch(get, resultParser);
    }

    @Override
    public HttpRequestBase newRequest(String url) {
        return new HttpGet(url);
    }

    @Override
    public CloseableHttpClient getHttpClient() {
        return HttpClientBuilder.concurrentClient();
    }
}
