package com.moonsister.tcjy.bean;

import com.moonsister.tcjy.utils.EnumConstant;

import java.util.List;

/**
 * Created by jb on 2016/9/27.
 */
public class EngagemengRecommendBean extends BaseBean {

    /**
     * uid : 144122
     * nickname : 玉冰清
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/10/2016-07-08/577fb2fb763a6.png
     * birthday :
     * sex : 2
     * isauth : 3
     * profession : 客服
     * signature : 新人加入，请记得关注我哦。
     * age : 22岁
     * fansnum : 粉丝：32
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
        private String nickname;
        private String face;
        private String birthday;
        private int sex;
        private int isauth;
        private String profession;
        private String signature;
        private String age;
        private String fansnum;
        private String weight;
        private String height;
        private String residence;
        private EnumConstant.EngegamentType mEngegamentType;

        public EnumConstant.EngegamentType getEngegamentType() {
            return mEngegamentType;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getResidence() {
            return residence;
        }

        public void setResidence(String residence) {
            this.residence = residence;
        }

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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getIsauth() {
            return isauth;
        }

        public void setIsauth(int isauth) {
            this.isauth = isauth;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getFansnum() {
            return fansnum;
        }

        public void setFansnum(String fansnum) {
            this.fansnum = fansnum;
        }

        public void setEngegamentType(EnumConstant.EngegamentType engegamentType) {
            mEngegamentType = engegamentType;
        }
    }
}
