package com.bingoabin.utils.dto;

public class ApiRespAndErrorBase<B, T> {

    private Integer errCode;
    private String errMsg;
    private B errDetails;
    private T data;

    public ApiRespAndErrorBase() {
    }

    public ApiRespAndErrorBase(Integer errCode, String errMsg, B errDetails, T data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.errDetails = errDetails;
        this.data = data;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public B getErrDetails() {
        return errDetails;
    }

    public void setErrDetails(B errDetails) {
        this.errDetails = errDetails;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiRespAndErrorBase{" +
                "errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                ", errDetails=" + errDetails +
                ", data=" + data +
                '}';
    }
}
