package com.hickey.network.bean;

/**
 * Created by x on 2016/8/31.
 */
public class RegOneBean extends BaseBean {

    /**
     * authcode : e1fce6d739d8b38642ced3fc237e8d24
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String authcode;
        private String uid;
        public String getAuthcode() {
            return authcode;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setAuthcode(String authcode) {
            this.authcode = authcode;
        }
    }
}
