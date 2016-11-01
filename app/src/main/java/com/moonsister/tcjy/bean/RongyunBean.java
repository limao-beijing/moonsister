package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/6/26.
 */
public class RongyunBean extends BaseBean {
    private RongyunData data;

    public RongyunData getData() {
        return data;
    }

    public void setData(RongyunData data) {
        this.data = data;
    }

    public static class RongyunData {
        private String uid;
        private String token;
        private String face;
        private String nickname;
        private String sex;
        private String apply_status;
        private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getApply_status() {
            return apply_status;
        }

        public void setApply_status(String apply_status) {
            this.apply_status = apply_status;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
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
    }
}
