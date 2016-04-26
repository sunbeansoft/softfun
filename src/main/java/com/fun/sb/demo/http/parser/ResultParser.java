package com.fun.sb.demo.http.parser;

import org.apache.http.StatusLine;

/**
 * 处理返回集
 * User: shangbin01
 * Date: 16/4/25
 * Time: 下午10:48
 */
public interface ResultParser<T> {

    /**
     * 处理结果
     *
     * @param result
     * @return
     */
    public T handleResult(String result);

    /**
     * 处理返回值非200问题
     * @param statusLine
     */
    public void handleError(StatusLine statusLine);

    /**
     * 处理异常
     *
     * @param e 异常
     */
    public void handleException(Exception e);
}
