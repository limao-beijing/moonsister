package com.hickey.network.bean;

import java.util.List;

/**
 * Created by pc on 2016/6/3.
 */
public class GoodSelectBaen extends BaseBean {

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static  class Data extends BaseDataBean {
        private String face;
        private String nickname;
        private String sex;
        private String place;
        private String fansnum;
        private String uid;
        private String anum;

        public String getAnum() {
            return anum;
        }

        public void setAnum(String anum) {
            this.anum = anum;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUserid() {
            return uid;
        }

        public void setUserid(String userid) {
            this.uid = userid;
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

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getFansnum() {
            return fansnum;
        }

        public void setFansnum(String fansnum) {
            this.fansnum = fansnum;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "face='" + face + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", sex='" + sex + '\'' +
                    ", place='" + place + '\'' +
                    ", fansnum='" + fansnum + '\'' +
                    '}';
        }
    }

}
