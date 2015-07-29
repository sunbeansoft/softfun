package com.fun.sb.demo.netty;

public interface FetcherCallback {
    void onData(Data data) throws Exception;

    void onError(Throwable cause);
}