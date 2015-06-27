package com.qinziwanba.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by wangzhiguo on 15/6/27.
 */
public class WanbaLogger {
    private static final Logger traceLogger = LoggerFactory.getLogger("trace");
    private static final Logger debugLogger = LoggerFactory.getLogger("debug");
    private static final Logger infoLogger = LoggerFactory.getLogger("info");
    private static final Logger warnLogger = LoggerFactory.getLogger("warn");
    private static final Logger errorLogger = LoggerFactory.getLogger("error");

    public WanbaLogger() {
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    private static void requestTrace(String traceId, WanbaLogger.Endpoint endpoint, String category, String message, Object... args) {
        String pattern = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL|%2$s|%3$s|%4$s|%5$s", new Object[]{Long.valueOf(System.currentTimeMillis()), traceId, endpoint.name(), category, message});
        if(args.length > 0) {
            traceLogger.info(pattern, args);
        } else {
            traceLogger.info(pattern);
        }

    }

    public static void requestTrace(WanbaLogger.HandlerContext context, String category, String message, Object... args) {
        requestTrace(context.getTraceId(), context.getEndpoint(), category, message, args);
    }

    public static String requestTrace(String uid, Date requestTime, String traceId, WanbaLogger.Endpoint endpoint, WanbaLogger.LogType logType, String inputParam, String outputParam, String tid, String callUrl, String callMethod, long elapsedTime, WanbaLogger.LogStatus status, String callerIp, String localIp, Object... args) {
        String pattern = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL|%2$s|%3$s|%4$s|%5$s|%6$s|%7$s|%8$s|%9$s|%10$s|%11$s|%12$s|%13$s|%14$s|", new Object[]{requestTime, traceId, endpoint.name(), logType, tid, Long.valueOf(elapsedTime), status, callUrl, callMethod, callerIp, localIp, uid, parseShuXian(inputParam), parseShuXian(outputParam)});
        if(args.length > 0) {
            Object[] arr$ = args;
            int len$ = args.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object arg = arr$[i$];
                pattern = pattern.concat(String.valueOf(arg)).concat("|");
            }
        }

        traceLogger.info(pattern);
        return pattern;
    }

    private static String parseShuXian(String sourceStr) {
        return sourceStr == null?"null":sourceStr.replaceAll("\\|", "#");
    }

    public static void debug(String format, Object... arguments) {
        debugLogger.debug(format, arguments);
    }

    public static void debug(String msg, Throwable e) {
        debugLogger.debug(msg, e);
    }

    public static void info(String format, Object... arguments) {
        infoLogger.info(format, arguments);
    }

    public static void info(String msg, Throwable e) {
        infoLogger.info(msg, e);
    }

    public static void warn(String format, Object... arguments) {
        warnLogger.warn(format, arguments);
    }

    public static void warn(String msg, Throwable e) {
        warnLogger.warn(msg, e);
    }

    public static void error(String format, Object... arguments) {
        errorLogger.error(format, arguments);
    }

    public static void error(String msg, Throwable e) {
        errorLogger.error(msg, e);
    }

    public static class HandlerContext {
        protected final String traceId;
        protected final WanbaLogger.Endpoint endpoint;

        public HandlerContext(String traceId, WanbaLogger.Endpoint endpoint) {
            this.traceId = traceId;
            this.endpoint = endpoint;
        }

        public String getTraceId() {
            return this.traceId;
        }

        public WanbaLogger.Endpoint getEndpoint() {
            return this.endpoint;
        }
    }

    public static enum LogStatus {
        SUCCESS,
        FAILED,
        TIMEOUT;

        private LogStatus() {
        }
    }

    public static enum LogType {
        HTTP_SERVICE,
        SQL_CALL,
        HTTP_CALL;

        private LogType() {
        }
    }

    public static enum Endpoint {
        WEBSITE,
        CRAWLER,
        UNKNOWN;

        private Endpoint() {
        }
    }

}
