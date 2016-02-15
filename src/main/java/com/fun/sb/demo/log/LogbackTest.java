package com.fun.sb.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shangbin01 on 2015/12/2.
 */
public class LogbackTest {
    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(LogbackTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        logger.info("logback 成功了");
        logger.error("logback 成功了");
    }
}
