package com.hickey.network.bean;

/**
 * Created by jb on 2016/9/30.
 */

public class BannerBean extends BaseBean {

    /**
     * data : 这是公告：不夜城同学刚充值一年的VIP用户。小红同学充值了半年的VIP及用户。
     * code : 1
     * jump_url : http://3.yytbzs.cn:88/index.php/index/mmvip/jump_url
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String data;
        private String code;
        private String jump_url;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }
    }
}
