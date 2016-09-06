package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/9/2.
 */
public class PersonalMessageBean extends BaseBean{

    /**
     * rules : [{"field":"nickname","name":"昵称","edit":"1","isvip":"0","value":"mm1471248477864"},{"field":"signature","name":"个性签名","edit":"1","isvip":"0","value":"新人加入，请记得关注我哦。"},{"field":"sex","name":"性别","edit":"2","isvip":"0","value":1},{"field":"birthday","name":"出生年月","edit":"1","isvip":"0","value":""},{"field":"star_sign","name":"星座","edit":"1","isvip":"0","value":""},{"field":"birthplace","name":"籍贯","edit":"1","isvip":"0","value":""},{"field":"residence","name":"现居","edit":"1","isvip":"0","value":""},{"field":"profession","name":"职业","edit":"1","isvip":"0","value":""},{"field":"hobby","name":"兴趣爱好","edit":"1","isvip":"0","value":null},{"field":"self_image","name":"自我印象","edit":"1","isvip":"0","value":null},{"field":"ishouse","name":"是否有房","edit":"1","isvip":"0","value":null},{"field":"marital_status","name":"婚姻状况","edit":"1","isvip":"0","value":null},{"field":"distance_love","name":"接受异地恋","edit":"1","isvip":"0","value":null},{"field":"like_sex","name":"喜欢的异性","edit":"1","isvip":"0","value":null},{"field":"premarital_sex","name":"婚前性行为","edit":"1","isvip":"0","value":null}]
     * dlist : {"face":"http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png","nickname":"mm1471248477864","signature":"新人加入，请记得关注我哦。","sex":1,"birthday":"","star_sign":"","birthplace":"","residence":"","profession":"","hobby":null,"self_image":null,"ishouse":null,"marital_status":null,"distance_love":null,"like_sex":null,"premarital_sex":null}
     * baseinfo : {"nickname":"mm1471248477864","sex":1,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png","like_image":"http://mimei.oss-cn-beijing.aliyuncs.com/public/default-like-image.png","profession":"","birthday":"","signature":"新人加入，请记得关注我哦。","age":23,"vip_level":"0","isauth":"1","isfollow":"2"}
     * vipinfo : {"smobile":null,"qq":null,"weixin":null}
     */
    int userId;
    String userName;
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }


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
         * birthday :
         * star_sign :
         * birthplace :
         * residence :
         * profession :
         * hobby : null
         * self_image : null
         * ishouse : null
         * marital_status : null
         * distance_love : null
         * like_sex : null
         * premarital_sex : null
         */

        private DlistBean dlist;
        /**
         * nickname : mm1471248477864
         * sex : 1
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png
         * like_image : http://mimei.oss-cn-beijing.aliyuncs.com/public/default-like-image.png
         * profession :
         * birthday :
         * signature : 新人加入，请记得关注我哦。
         * age : 23
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
         * field : nickname
         * name : 昵称
         * edit : 1
         * isvip : 0
         * value : mm1471248477864
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
            private String star_sign;
            private String birthplace;
            private String residence;
            private String profession;
            private String hobby;
            private String self_image;
            private String ishouse;
            private String marital_status;
            private String distance_love;
            private String like_sex;
            private String premarital_sex;

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

            public String getStar_sign() {
                return star_sign;
            }

            public void setStar_sign(String star_sign) {
                this.star_sign = star_sign;
            }

            public String getBirthplace() {
                return birthplace;
            }

            public void setBirthplace(String birthplace) {
                this.birthplace = birthplace;
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

            public String getSelf_image() {
                return self_image;
            }

            public void setSelf_image(String self_image) {
                this.self_image = self_image;
            }

            public String getIshouse() {
                return ishouse;
            }

            public void setIshouse(String ishouse) {
                this.ishouse = ishouse;
            }

            public String getMarital_status() {
                return marital_status;
            }

            public void setMarital_status(String marital_status) {
                this.marital_status = marital_status;
            }

            public String getDistance_love() {
                return distance_love;
            }

            public void setDistance_love(String distance_love) {
                this.distance_love = distance_love;
            }

            public String getLike_sex() {
                return like_sex;
            }

            public void setLike_sex(String like_sex) {
                this.like_sex = like_sex;
            }

            public String getPremarital_sex() {
                return premarital_sex;
            }

            public void setPremarital_sex(String premarital_sex) {
                this.premarital_sex = premarital_sex;
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
            private String value;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
