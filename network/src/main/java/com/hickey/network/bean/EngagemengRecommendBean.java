package com.hickey.network.bean;


import com.hickey.tool.constant.EnumConstant;

import java.util.List;

/**
 * Created by jb on 2016/9/27.
 */
public class EngagemengRecommendBean extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        /**
         * uid : 144081
         * nickname : 麦
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/02/2016-07-12/578492210abdb.jpg
         * birthday :
         * height : 0
         * weight : 0
         * residence :
         * signature : 新人加入，请记得关注我哦。
         * isauth : 3
         * age : 22
         * vip_level : 0
         */

        private String uid;
        private String nickname;
        private String face;
        private String birthday;
        private String height;
        private String weight;
        private String residence;
        private String signature;
        private int isauth;
        private String age;
        private String vip_level;
        private EnumConstant.EngegamentType mEngegamentType;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
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

        public int getIsauth() {
            return isauth;
        }

        public void setIsauth(int isauth) {
            this.isauth = isauth;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getVip_level() {
            return vip_level;
        }

        public void setVip_level(String vip_level) {
            this.vip_level = vip_level;
        }

        public void setEngegamentType(EnumConstant.EngegamentType engegamentType) {
            mEngegamentType = engegamentType;
        }

        public EnumConstant.EngegamentType getEngegamentType() {
            return mEngegamentType;
        }
    }
}
