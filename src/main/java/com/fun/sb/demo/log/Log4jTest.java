package com.fun.sb.demo.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

/**
 * Created by shangbin01 on 2015/12/2.
 */
public class Log4jTest {

    private static Log log = LogFactory.getLog(Log4jTest.class);

    public void log() {
        log.debug("Debug info.");
        log.info("Info info");
        log.warn("Warn info");
        log.error("Error info");
        log.fatal("Fatal info");
    }

    public static void main(String[] args) {
        MDC.put("UserName", "jzj");
        MDC.put("opttype", "delete");
        Log4jTest test = new Log4jTest();
        test.log();
    }

}
