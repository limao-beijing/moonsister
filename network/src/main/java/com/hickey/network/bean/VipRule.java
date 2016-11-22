package com.hickey.network.bean;

/**
 * Created by jb on 2016/10/28.
 */
public class VipRule extends BaseBean {


    /**
     * info : VIP充值等级：
     29 3天体验，59 1个月
     129 3个月【送话费100】
     219 12个月【送话费200】
     终身509【送话费500】
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String info;
        private String info_top;

        public String getInfo_top() {
            return info_top;
        }

        public void setInfo_top(String info_top) {
            this.info_top = info_top;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
