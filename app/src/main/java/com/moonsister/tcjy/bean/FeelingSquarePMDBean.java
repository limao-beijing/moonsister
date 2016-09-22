package com.moonsister.tcjy.bean;

/**
 * Created by Administrator on 2016/9/21.
 */
public class FeelingSquarePMDBean extends BaseBean {


    /**
     * status : 100
     * msg : 您尚未绑定手机，为了您的账户安全请尽快绑定
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
