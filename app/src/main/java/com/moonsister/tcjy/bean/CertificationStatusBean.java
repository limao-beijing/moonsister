package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/7/1.
 */
public class CertificationStatusBean {

    /**
     * code : 1
     * msg : 拉取成功
     * data : {"status":"3"}
     */

    private String code;
    private String msg;
    /**
     * status : 3
     */

    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
