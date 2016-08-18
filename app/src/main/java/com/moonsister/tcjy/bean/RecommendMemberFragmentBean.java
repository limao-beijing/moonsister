package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by jb on 2016/8/15.
 */
public class RecommendMemberFragmentBean extends BaseBean {


    /**
     * uid : 130781
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/default/face/2084.jpg
     * nickname : 悟来悟去d
     * sex : 2
     * signature : 新人加入，请记得关注我哦。
     * profession : null
     * birthday : null
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
        private String face;
        private String nickname;
        private String sex;
        private String signature;
        private String profession;
        private String birthday;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
