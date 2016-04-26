package com.fun.sb.demo.http.fetcher;

/**
 * 资源获取器工厂方法
 * User: shangbin01
 * Date: 16/4/26
 * Time: 上午10:04
 */
public class Fetchers {

    /**
     * GET方法请求
     */
    public static final GetResourceFetcher GET = new GetResourceFetcher();
    /**
     * POST方法请求
     */
    public static final PostResourceFetcher POST = new PostResourceFetcher();
}
