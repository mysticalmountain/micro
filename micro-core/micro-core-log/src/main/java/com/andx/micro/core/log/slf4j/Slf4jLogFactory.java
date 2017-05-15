package com.andx.micro.core.log.slf4j;

import com.andx.micro.api.log.Log;
import com.andx.micro.api.log.LogFactory;
import com.andx.micro.core.log.log4j.Log4jLog;

/**
 * Created by andongxu on 16-7-28.
 */
public class Slf4jLogFactory implements LogFactory {
    public Log getLog(Class<?> c) {
        return new Slf4jLog(c);
    }

    public Log getLog(String name) {
        return new Slf4jLog(name);
    }

    private static Slf4jLogFactory log4jLogFactory;

    static {
        log4jLogFactory = new Slf4jLogFactory();
    }

    public static LogFactory getLogFactory() {
        return log4jLogFactory;
    }

}
