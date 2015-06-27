package com.qinziwanba.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhiguo on 15/6/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WanbaResult {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result_data")
    private Object resultData;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public Map<String, Object> toMap() {
        HashMap resultMap = new HashMap();
        resultMap.put("error_code", this.errorCode);
        resultMap.put("message", this.message);
        resultMap.put("result_data", this.resultData);
        return resultMap;
    }

    public String toString() {
        return this.toMap().toString();
    }

    @JsonProperty
    public boolean isSuccess() {
        return this.errorCode != null && (this.errorCode.equals("0") || this.errorCode.equals(WanbaErrorCode.WANBA_SUCCESS.getErrorCode()));
    }
}
