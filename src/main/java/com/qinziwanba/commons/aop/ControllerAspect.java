package com.qinziwanba.commons.aop;

import com.qinziwanba.commons.WanbaConstants;
import com.qinziwanba.commons.WanbaException;
import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.commons.WanbaResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by xingren23 on 15/3/16.
 */
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Pointcut("execution(public * com.qinziwanba.crawler.controller.*Controller.*(..))")
    public void crawlerPointcut() {
    }

    @Pointcut("execution(public * com.qinziwanba.crawler.controller.*Controller.*(..))")
    public void apiPointcut() {
    }

    @Around("requestMapping() && crawlerPointcut()")
    public Object aroundCrawlerInvock(ProceedingJoinPoint joinPoint) throws Throwable {
        return aroundMethod(joinPoint);
    }

    @Around("requestMapping() && apiPointcut()")
    public Object aroundApiInvock(ProceedingJoinPoint joinPoint) throws Throwable {
        return aroundMethod(joinPoint);
    }

    private Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        // 只会拦截 Controller 最后一个参数是 HttpServletRequest 的 接口调用
        Object requestObj = null;
        if (joinPoint.getArgs().length >= 1) {
            requestObj = joinPoint.getArgs()[joinPoint.getArgs().length - 1];
        }
        if (requestObj == null || !(requestObj instanceof HttpServletRequest)) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = (HttpServletRequest) requestObj;

        Exception error = null;
        Object result = null;
        String traceId = null;
        try {
            // 请求前参数
            Date startTime = new Date();
            traceId = request.getHeader(WanbaConstants.WANBA_HTTP_TRACEID);
            String url = request.getRequestURL().toString();
            String callIp = getRemoteIpAdress(request);
            String localIp = request.getLocalAddr();

            String callMethod = joinPoint.getSignature().toLongString();
            String inputParam = getInputParam(joinPoint.getArgs());

            // 执行
            try {
                result = joinPoint.proceed();
            } catch (Exception e) {
                error = e;
            }

            // 请求后参数
            long eTime = System.currentTimeMillis() - startTime.getTime();
            String outParam = null;
            boolean bizSuccess = false;
            // 判断是否成功，通过业务标志和异常判断
            if (result != null) {
                if (result instanceof WanbaException) {
                    bizSuccess = ((WanbaResult) result).isSuccess();
                    outParam = ((WanbaResult) result).toString();
                } else {
                    outParam = result.toString();
                }
            }
            WanbaLogger.LogStatus logStatus = bizSuccess && (error == null) ? WanbaLogger.LogStatus.SUCCESS : WanbaLogger.LogStatus.FAILED;
            // TODO:超时处理？

            // 打印日志
            WanbaLogger.requestTrace(null, startTime, traceId, WanbaLogger.Endpoint.CRAWLER, WanbaLogger.LogType.HTTP_SERVICE, inputParam, outParam, null, url, callMethod, eTime, logStatus, callIp, localIp);
        } catch (Exception e) {
            // 错误信息中加个traceid便于跟踪
            WanbaLogger.error("TraceAopError,traceId:".concat(String.valueOf(traceId)), e);
        } finally {
            if (error != null) {
                throw error;
            }
        }
        return result;
    }

    // 拼接入参参数
    private String getInputParam(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            if (arg != null) {
                sb.append(arg.toString());
            } else {
                sb.append("null");
            }
        }
        return sb.toString();
    }

    /**
     * 获得客户端IP
     *
     * @return String
     */
    private String getRemoteIpAdress(HttpServletRequest request) {
        // 如果通过反向代理访问的服务器,则先取x-forwarded-for的header
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        }
        // 如果经过了多级代理,x-forwarded-for中有多个IP地址,则取第一个不为unknown的
        if (ip != null && ip.indexOf(",") != -1) {
            String[] address = ip.split(",");
            ip = address[0];
            for (int i = 0; i < address.length; i++) {
                if (!"unknown".equalsIgnoreCase(address[i].trim())) {
                    ip = address[i].trim();
                    break;
                }
            }
        }
        return ip;
    }

}

