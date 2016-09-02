package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/9/2.
 */
public class PersonalMessageBean extends BaseBean{


    /**
     * rules : [{"field":"smobile","name":"手机","edit":"2","isvip":"1"},{"field":"qq","name":"QQ","edit":"1","isvip":"1"},{"field":"weixin","name":"微信","edit":"1","isvip":"1"},{"field":"face","name":"头像","edit":"1","isvip":"0"},{"field":"nickname","name":"昵称","edit":"1","isvip":"0"},{"field":"signature","name":"个性签名","edit":"1","isvip":"0"},{"field":"sex","name":"性别","edit":"2","isvip":"0"},{"field":"birthday","name":"出生年月","edit":"1","isvip":"0"},{"field":"height","name":"身高","edit":"1","isvip":"0"},{"field":"weight","name":"体重","edit":"1","isvip":"0"},{"field":"residence","name":"现居","edit":"1","isvip":"0"},{"field":"profession","name":"职业","edit":"1","isvip":"0"},{"field":"hobby","name":"兴趣爱好","edit":"1","isvip":"0"}]
     * dlist : {"face":"http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png","nickname":"mm1471248477864","signature":"新人加入，请记得关注我哦。","sex":1,"birthday":null,"height":"0","weight":"0","residence":null,"profession":null,"hobby":null}
     * baseinfo : {"nickname":"mm1471248477864","sex":1,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png","like_image":"http://mimei.oss-cn-beijing.aliyuncs.com/public/default-like-image.png","profession":null,"birthday":null,"signature":"新人加入，请记得关注我哦。","age":18,"vip_level":"0","isauth":"1","isfollow":"2"}
     * vipinfo : {"smobile":null,"qq":null,"weixin":null}
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
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png
         * nickname : mm1471248477864
         * signature : 新人加入，请记得关注我哦。
         * sex : 1
         * birthday : null
         * height : 0
         * weight : 0
         * residence : null
         * profession : null
         * hobby : null
         */

        private DlistBean dlist;
        /**
         * nickname : mm1471248477864
         * sex : 1
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png
         * like_image : http://mimei.oss-cn-beijing.aliyuncs.com/public/default-like-image.png
         * profession : null
         * birthday : null
         * signature : 新人加入，请记得关注我哦。
         * age : 18
         * vip_level : 0
         * isauth : 1
         * isfollow : 2
         */

        private BaseinfoBean baseinfo;
        /**
         * smobile : null
         * qq : null
         * weixin : null
         */

        private VipinfoBean vipinfo;
        /**
         * field : smobile
         * name : 手机
         * edit : 2
         * isvip : 1
         */

        private List<RulesBean> rules;

        public DlistBean getDlist() {
            return dlist;
        }

        public void setDlist(DlistBean dlist) {
            this.dlist = dlist;
        }

        public BaseinfoBean getBaseinfo() {
            return baseinfo;
        }

        public void setBaseinfo(BaseinfoBean baseinfo) {
            this.baseinfo = baseinfo;
        }

        public VipinfoBean getVipinfo() {
            return vipinfo;
        }

        public void setVipinfo(VipinfoBean vipinfo) {
            this.vipinfo = vipinfo;
        }

        public List<RulesBean> getRules() {
            return rules;
        }

        public void setRules(List<RulesBean> rules) {
            this.rules = rules;
        }

        public static class DlistBean {
            private String face;
            private String nickname;
            private String signature;
            private int sex;
            private String birthday;
            private String height;
            private String weight;
            private String residence;
            private String profession;
            private String hobby;

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

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
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

            public String getProfession() {
                return profession;
            }

            public void setProfession(String profession) {
                this.profession = profession;
            }

            public String getHobby() {
                return hobby;
            }

            public void setHobby(String hobby) {
                this.hobby = hobby;
            }
        }

        public static class BaseinfoBean {
            private String nickname;
            private int sex;
            private String face;
            private String like_image;
            private String profession;
            private String birthday;
            private String signature;
            private int age;
            private String vip_level;
            private String isauth;
            private String isfollow;

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

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getVip_level() {
                return vip_level;
            }

            public void setVip_level(String vip_level) {
                this.vip_level = vip_level;
            }

            public String getIsauth() {
                return isauth;
            }

            public void setIsauth(String isauth) {
                this.isauth = isauth;
            }

            public String getIsfollow() {
                return isfollow;
            }

            public void setIsfollow(String isfollow) {
                this.isfollow = isfollow;
            }
        }

        public static class VipinfoBean {
            private String smobile;
            private String qq;
            private String weixin;

            public String getSmobile() {
                return smobile;
            }

            public void setSmobile(String smobile) {
                this.smobile = smobile;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getWeixin() {
                return weixin;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }
        }

        public static class RulesBean {
            private String field;
            private String name;
            private String edit;
            private String isvip;

            public String getField() {
                return field;
            }

            public void setField(String field) {
                this.field = field;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEdit() {
                return edit;
            }

            public void setEdit(String edit) {
                this.edit = edit;
            }

            public String getIsvip() {
                return isvip;
            }

            public void setIsvip(String isvip) {
                this.isvip = isvip;
            }
        }
    }
}
