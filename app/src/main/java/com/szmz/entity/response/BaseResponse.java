package com.szmz.entity.response;

/**
 * Created by bz on 2017/9/3.
 */

public class BaseResponse {
    private int ErrorCode;
    private String ErrorMessage;
    private int TotalNum;

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public int getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(int totalNum) {
        TotalNum = totalNum;
    }
}
