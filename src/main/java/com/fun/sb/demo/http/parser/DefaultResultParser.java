package com.fun.sb.demo.http.parser;

import org.apache.http.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认结果解析器 不处理任何错误
 * User: shangbin01
 * Date: 16/4/26
 * Time: 上午12:49
 */
public abstract class DefaultResultParser<T> implements ResultParser<T> {

    private static Logger logger = LoggerFactory.getLogger(DefaultResultParser.class);

    @Override
    public void handleError(StatusLine statusLine) {
        logger.error("http请求错误状态码:" + statusLine.getStatusCode());
        //do nothing
    }

    @Override
    public void handleException(Exception e) {
        logger.error("http请求连接异常", e);
        //do nothing
    }
}
