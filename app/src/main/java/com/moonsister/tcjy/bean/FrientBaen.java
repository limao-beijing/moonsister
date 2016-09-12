package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by jb on 2016/7/8.
 */
public class FrientBaen extends BaseBean {


    /**
     * nickname : 蓉儿
     * uid : 104716
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/10/2016-07-07/577e6d0d4a0d1.png
     * signature : null
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String nickname;
        private String uid;
        private String face;
        private String signature;
        private String isfollow;
        private String isnew;
        private String vip_level;


        public String getVip_level() {
            return vip_level;
        }

        public void setVip_level(String vip_level) {
            this.vip_level = vip_level;
        }

        public String getIsnew() {
            return isnew;
        }

        public void setIsnew(String isnew) {
            this.isnew = isnew;
        }

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }


    }
}
