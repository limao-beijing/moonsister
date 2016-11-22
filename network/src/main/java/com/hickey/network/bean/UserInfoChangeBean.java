package com.hickey.network.bean;

/**
 * Created by jb on 2016/7/11.
 */
public class UserInfoChangeBean  extends BaseBean{

    /**
     * face : null
     * nickname : mm1467984846929
     * sex : 1
     * birthday : null
     * residence : null
     * signature : null
     * self_intro : null
     * degree : null
     * height : 0
     * weight : 0
     * profession : null
     * isauth : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String face;
        private String nickname;
        private int sex;
        private String birthday;
        private String residence;
        private String signature;
        private String self_intro;
        private String degree;
        private String height;
        private String weight;
        private String profession;
        private String isauth;

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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getResidence() {
            return residence;
        }

        public void setResidence(String residence) {
            this.residence = residence;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getSelf_intro() {
            return self_intro;
        }

        public void setSelf_intro(String self_intro) {
            this.self_intro = self_intro;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
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
    }
}
