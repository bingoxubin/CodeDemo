package com.bingoabin.utils.dto;

/**
 * @author HanJunsheng
 * @date 2021/7/16
 * @copyright sankuai.com
 */
public class ApiRespBase<T> {

    private Integer code;
    private String msg;
    private T data;

    public ApiRespBase() {
    }

    public ApiRespBase(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
