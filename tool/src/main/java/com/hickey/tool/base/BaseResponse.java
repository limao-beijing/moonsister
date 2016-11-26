package com.hickey.tool.base;

import java.io.Serializable;

/**
 * 返回数据最外层模型
 * Created by ganyao on 2016/10/27.
 */

public class BaseResponse<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
