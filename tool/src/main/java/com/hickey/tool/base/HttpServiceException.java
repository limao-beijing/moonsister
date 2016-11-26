package com.hickey.tool.base;

/**
 * Created by jb on 2016/11/25.
 */
public class HttpServiceException extends BaseException {
    private int code;
    private String msg;

    public HttpServiceException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
