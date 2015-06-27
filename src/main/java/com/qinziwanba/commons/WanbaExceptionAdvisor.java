package com.qinziwanba.commons;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xingren23 on 15/2/9.
 */
@ControllerAdvice
public class WanbaExceptionAdvisor {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public WanbaResult handleException(Exception ex, HttpServletRequest request) {
        WanbaResult wanbaResult = new WanbaResult();

        if (ex instanceof WanbaException) {
            wanbaResult.setErrorCode(String.valueOf(((WanbaException) ex).getWanbaErrorCode().getErrorCode()));
            wanbaResult.setMessage(ex.getMessage());
        }else {
            wanbaResult.setErrorCode(WanbaErrorCode.WANBA_UNKNOWN_ERROR.getErrorCodeString());
            wanbaResult.setMessage(ex.getMessage());
        }

        WanbaLogger.error("handle exception {}", wanbaResult);
        return wanbaResult;
    }
}
