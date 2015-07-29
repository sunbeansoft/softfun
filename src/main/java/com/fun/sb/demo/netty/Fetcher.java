package com.fun.sb.demo.netty;

public interface Fetcher {
    void fetchData(FetcherCallback callback);
}