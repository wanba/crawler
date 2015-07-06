package com.qinziwanba.commons;

/**
 * Created by wangzhiguo on 15/6/27.
 */
public enum WanbaErrorCode {

    WANBA_SUCCESS(10000, "操作成功"),
    WANBA_UNKNOWN_ERROR(10001, "未知异常"),
    WANBA_PARAM_ISBLACK(10002, "参数不能为空"),
    WANBA_OLD_PASSWORD_INVALID(10003, "原始密码不正确"),
    WANBA_PASSWORD_INVALID(10004, "密码不正确");

    private int errorCode;
    private String message;

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorCodeString() {
        return String.valueOf(this.errorCode);
    }

    public String getMessage() {
        return this.message;
    }

    private WanbaErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String toString() {
        return "WanbaErrorCode{wanbaErrorCode=" + this.errorCode + ", message=\'" + this.message + '\'' + '}';
    }
}
