package com.hickey.network.bean;

import java.util.List;

/**
 * Created by jb on 2016/7/10.
 */
public class SearchReasonBaen extends BaseBean {

    /**
     * uid : 104699
     * isfollow : 2
     * nickname : 井小西
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/09/2016-07-07/577e5a317d027.jpg
     * signature : null
     * sex : 2
     * fansnum : 3
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String uid;
        private String isfollow;
        private String nickname;
        private String face;
        private String signature;
        private String sex;
        private String fansnum;


        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getFansnum() {
            return fansnum;
        }

        public void setFansnum(String fansnum) {
            this.fansnum = fansnum;
        }
    }
}
