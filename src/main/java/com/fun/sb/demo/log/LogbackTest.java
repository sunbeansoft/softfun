package com.fun.sb.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shangbin01 on 2015/12/2.
 */
public class LogbackTest {
    //����һ��ȫ�ֵļ�¼����ͨ��LoggerFactory��ȡ
    private final static Logger logger = LoggerFactory.getLogger(LogbackTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        logger.info("logback �ɹ���");
        logger.error("logback �ɹ���");
    }
}
