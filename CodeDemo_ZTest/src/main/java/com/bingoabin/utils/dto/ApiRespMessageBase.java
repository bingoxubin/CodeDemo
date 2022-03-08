package com.bingoabin.utils.dto;

/**
 * @author HanJunsheng
 * @date 2021/7/16
 * @copyright sankuai.com
 */
public class ApiRespMessageBase<T> {

    private Integer code;
    private String message;
    private T data;

    public ApiRespMessageBase() {
    }

    public ApiRespMessageBase(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "XTRespBase{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
