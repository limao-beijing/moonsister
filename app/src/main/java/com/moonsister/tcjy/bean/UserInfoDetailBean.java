package com.moonsister.tcjy.bean;

import java.io.Serializable;

/**
 * Created by jb on 2016/6/20.
 */
public class UserInfoDetailBean extends BaseBean {
    private UserInfoDetailDataBean data;

    public UserInfoDetailDataBean getData() {
        return data;
    }

    public void setData(UserInfoDetailDataBean data) {
        this.data = data;
    }

    public static class UserInfoDetailDataBean extends BaseDataBean {

        private Addons addons;
        private Object guard;
        private String follow;
        private Baseinfo baseinfo;
        private String uid;
        private int dynamicType = 1;

        public int getDynamicType() {
            return dynamicType;
        }

        public void setDynamicType(int dynamicType) {
            this.dynamicType = dynamicType;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setGuard(Object guard) {
            this.guard = guard;
        }

        public Baseinfo getBaseinfo() {
            return baseinfo;
        }

        public void setBaseinfo(Baseinfo baseinfo) {
            this.baseinfo = baseinfo;
        }

        public static class Baseinfo implements Serializable {
            private String nickname;
            private int sex;
            private String face;
            private String like_image;
            // 1 已审核   2审核中  3 未审核
            private String isverify;
            private String profession;
            private String age;
            private String latest_total;
            private String latest_free;
            private String latest_vip;
            private String signature;
            private String tags;

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getLatest_total() {
                return latest_total;
            }

            public void setLatest_total(String latest_total) {
                this.latest_total = latest_total;
            }

            public String getLatest_free() {
                return latest_free;
            }

            public void setLatest_free(String latest_free) {
                this.latest_free = latest_free;
            }

            public String getLatest_vip() {
                return latest_vip;
            }

            public void setLatest_vip(String latest_vip) {
                this.latest_vip = latest_vip;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getProfession() {
                return profession;
            }

            public void setProfession(String profession) {
                this.profession = profession;
            }

            public String getIsverify() {
                return isverify;
            }

            public void setIsverify(String isverify) {
                this.isverify = isverify;
            }

            public String getLike_image() {
                return like_image;
            }

            public void setLike_image(String like_image) {
                this.like_image = like_image;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getNickname() {
                return nickname;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getSex() {
                return sex;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getFace() {
                return face;
            }

            public void setLikeImage(String likeImage) {
                this.like_image = likeImage;
            }

            public String getLikeImage() {
                return like_image;
            }
        }

        public static class Addons {
            private String ufann;
            private String ufoln;
            private String ulatn;
            private String uflon;
            private String income_all;
            private String income_today;

            public String getIncome_all() {
                return income_all;
            }

            public void setIncome_all(String income_all) {
                this.income_all = income_all;
            }

            public String getIncome_today() {
                return income_today;
            }

            public void setIncome_today(String income_today) {
                this.income_today = income_today;
            }

            public String getUfann() {
                return ufann;
            }

            public void setUfann(String ufann) {
                this.ufann = ufann;
            }

            public String getUfoln() {
                return ufoln;
            }

            public void setUfoln(String ufoln) {
                this.ufoln = ufoln;
            }

            public String getUlatn() {
                return ulatn;
            }

            public void setUlatn(String ulatn) {
                this.ulatn = ulatn;
            }

            public String getUflon() {
                return uflon;
            }

            public void setUflon(String uflon) {
                this.uflon = uflon;
            }
        }

        public Object getGuard() {
            return guard;
        }

        public void setGuard(String guard) {
            this.guard = guard;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public Addons getAddons() {
            return addons;
        }

        public void setAddons(Addons addons) {
            this.addons = addons;
        }


    }

}
