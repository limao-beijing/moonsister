package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/9/8.
 */
public class PingbiBean extends BaseBean{


    /**
     * uid : 104005
     * nickname : 琳娜
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/04/2016-08-05/57a44ea2a3773.jpg
     * sex : 2
     * birthday : 1992-12-16
     * isauth : 1
     * signature : 涐那様愛伱，眞是沒心沒肺。
     * age : 24
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private int uid;
        private String nickname;
        private String face;
        private int sex;
        private String birthday;
        private int isauth;
        private String signature;
        private int age;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public int getIsauth() {
            return isauth;
        }

        public void setIsauth(int isauth) {
            this.isauth = isauth;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
