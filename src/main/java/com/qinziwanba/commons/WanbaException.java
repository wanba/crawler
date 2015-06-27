package com.qinziwanba.commons;

/**
 * Created by wangzhiguo on 15/6/27.
 */
public class WanbaException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    protected WanbaErrorCode wanbaErrorCode;
    protected String details;

    public WanbaErrorCode getWanbaErrorCode() {
        return this.wanbaErrorCode;
    }

    public String getDetails() {
        return this.details;
    }

    public WanbaException(WanbaErrorCode wanbaErrorCode) {
        super(wanbaErrorCode.getMessage());
        this.wanbaErrorCode = wanbaErrorCode;
    }

    public WanbaException(WanbaErrorCode wanbaErrorCode, String details) {
        super(wanbaErrorCode.getMessage());
        this.wanbaErrorCode = wanbaErrorCode;
        this.details = details;
    }

    public WanbaException(WanbaErrorCode wanbaErrorCode, Throwable cause) {
        super(wanbaErrorCode.getMessage(), cause);
        this.wanbaErrorCode = wanbaErrorCode;
    }

    public WanbaException(WanbaErrorCode wanbaErrorCode, String details, Throwable cause) {
        super(wanbaErrorCode.getMessage(), cause);
        this.wanbaErrorCode = wanbaErrorCode;
        this.details = details;
    }

    public String toString() {
        return "WanbaException{wanbaErrorCode=" + this.wanbaErrorCode + ", details=\'" + this.details + '\'' + ", errorMessage=\'" + this.getMessage() + "\'}";
    }

}
