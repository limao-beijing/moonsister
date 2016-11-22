package com.hickey.network.bean;

import java.util.List;

/**
 * Created by jb on 2016/7/8.
 */
public class FrientBaen extends BaseBean {


    /**
     * nickname : 苏子墨
     * uid : 104020
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/05/2016-08-05/57a455d6079ed.jpg
     * signature : 我不会说情话，但我说的都是真心话.
     * profession : 公关
     * isauth : 1
     * isfollow : 1
     * isnew : 2
     * vip_level : 0
     * isshield : 2
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
        private String profession;
        private String isauth;
        private String isfollow;
        private String isnew;
        private String vip_level;
        private String isshield;

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

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getIsauth() {
            return isauth;
        }

        public void setIsauth(String isauth) {
            this.isauth = isauth;
        }

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }

        public String getIsnew() {
            return isnew;
        }

        public void setIsnew(String isnew) {
            this.isnew = isnew;
        }

        public String getVip_level() {
            return vip_level;
        }

        public void setVip_level(String vip_level) {
            this.vip_level = vip_level;
        }

        public String getIsshield() {
            return isshield;
        }

        public void setIsshield(String isshield) {
            this.isshield = isshield;
        }
    }
}
