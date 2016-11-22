package com.hickey.network.bean;

/**
 * Created by jb on 2016/8/18.
 */
public class UserPermissionBean extends BaseBean {


    /**
     * isauth : 3
     * vip : 2
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int isauth;
        private int vip;

        public int getIsauth() {
            return isauth;
        }

        public void setIsauth(int isauth) {
            this.isauth = isauth;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }
    }
}
