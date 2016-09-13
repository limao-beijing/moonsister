package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/9/8.
 */
public class UserDetailBean extends BaseBean {

    /**
     * baseinfo : {"nickname":"json","sex":1,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160804/18/14703055089420276.jpg","like_image":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160812/13/14709792952357606.jpg","profession":"白领","birthday":"1991-01-31","signature":"Uzbek","residence":"成都","age":19,"tags":"帅哥|||美女|||测试|||心灵鸡汤|||黑暗","latest_total":56,"latest_free":19,"latest_vip":37,"isverify":1,"vip_level":0}
     * addons : {"ufann":343,"ufoln":182,"ulatn":58,"uflon":0,"income_all":8263.02,"income_today":1.5}
     * guard : []
     * follow : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * nickname : json
         * sex : 1
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160804/18/14703055089420276.jpg
         * like_image : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160812/13/14709792952357606.jpg
         * profession : 白领
         * birthday : 1991-01-31
         * signature : Uzbek
         * residence : 成都
         * age : 19
         * tags : 帅哥|||美女|||测试|||心灵鸡汤|||黑暗
         * latest_total : 56
         * latest_free : 19
         * latest_vip : 37
         * isverify : 1
         * vip_level : 0
         */

        private BaseinfoBean baseinfo;
        /**
         * ufann : 343
         * ufoln : 182
         * ulatn : 58
         * uflon : 0
         * income_all : 8263.02
         * income_today : 1.5
         */

        private AddonsBean addons;
        private int follow;
        private List<?> guard;

        public BaseinfoBean getBaseinfo() {
            return baseinfo;
        }

        public void setBaseinfo(BaseinfoBean baseinfo) {
            this.baseinfo = baseinfo;
        }

        public AddonsBean getAddons() {
            return addons;
        }

        public void setAddons(AddonsBean addons) {
            this.addons = addons;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public List<?> getGuard() {
            return guard;
        }

        public void setGuard(List<?> guard) {
            this.guard = guard;
        }

        public static class BaseinfoBean {
            private String nickname;
            private int sex;
            private String face;
            private String like_image;
            private String profession;
            private String birthday;
            private String signature;
            private String residence;
            private String age;
            private String tags;
            private String latest_total;
            private String latest_free;
            private String latest_vip;
            private String isverify;
            private String vip_level;

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

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getLike_image() {
                return like_image;
            }

            public void setLike_image(String like_image) {
                this.like_image = like_image;
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

            public String getResidence() {
                return residence;
            }

            public void setResidence(String residence) {
                this.residence = residence;
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

            public String getIsverify() {
                return isverify;
            }

            public void setIsverify(String isverify) {
                this.isverify = isverify;
            }

            public String getVip_level() {
                return vip_level;
            }

            public void setVip_level(String vip_level) {
                this.vip_level = vip_level;
            }
        }

        public static class AddonsBean {
            private String ufann;
            private String ufoln;
            private String ulatn;
            private String uflon;
            private String income_all;
            private String income_today;

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
        }
    }
}
