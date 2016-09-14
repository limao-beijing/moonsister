package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/9/2.
 */
public class PersonalMessageBean extends BaseBean{


    /**
     * rules : [{"field":"like_image","name":"背景","edit":"1","isvip":"0","isshow":"1","value":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/04/2016-07-28/5799c429c9a84.jpg"},{"field":"nickname","name":"昵称","edit":"1","isvip":"0","isshow":"1","value":"瑶瑶"},{"field":"signature","name":"个性签名","edit":"1","isvip":"0","isshow":"1","value":"不解风情的男人太多\u2026\u2026"},{"field":"sex","name":"性别","edit":"2","isvip":"0","isshow":"1","value":2},{"field":"birthday","name":"出生年月","edit":"1","isvip":"0","isshow":"1","value":"1991-11-12"},{"field":"star_sign","name":"星座","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"height","name":"身高","edit":"1","isvip":"0","isshow":"1","value":"165"},{"field":"weight","name":"体重","edit":"1","isvip":"0","isshow":"1","value":"60"},{"field":"salary","name":"月薪","edit":"1","isvip":"0","isshow":"1","value":"20000以上"},{"field":"degree","name":"学历","edit":"1","isvip":"0","isshow":"1","value":"专科以下"},{"field":"birthplace","name":"籍贯","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"residence","name":"现居","edit":"1","isvip":"0","isshow":"1","value":"北京"},{"field":"profession","name":"职业","edit":"1","isvip":"0","isshow":"1","value":"媒体人"},{"field":"hobby","name":"兴趣爱好","edit":"1","isvip":"0","isshow":"1","value":"萌宠"},{"field":"self_image","name":"自我印象","edit":"1","isvip":"0","isshow":"1","value":null},{"field":"ishouse","name":"是否有房","edit":"1","isvip":"0","isshow":"1","value":"有房"},{"field":"marital_status","name":"情感状态","edit":"1","isvip":"0","isshow":"1","value":"恋爱中"},{"field":"distance_love","name":"接受异地恋","edit":"1","isvip":"0","isshow":"1","value":"可以接受"},{"field":"like_sex","name":"喜欢的异性","edit":"1","isvip":"0","isshow":"1","value":"落落大方"},{"field":"premarital_sex","name":"婚前性行为","edit":"1","isvip":"0","isshow":"1","value":"不能接受"},{"field":"raddress","name":"收货地址","edit":"1","isvip":"0","isshow":"1","value":null},{"field":"rcode","name":"邮编","edit":"1","isvip":"0","isshow":"1","value":null},{"field":"rname","name":"收货人","edit":"1","isvip":"0","isshow":"1","value":null},{"field":"t1","name":"预留项1","edit":"1","isvip":"0","isshow":"1","value":null},{"field":"t2","name":"预留项2","edit":"1","isvip":"0","isshow":"2","value":null},{"field":"t3","name":"预留项3","edit":"1","isvip":"0","isshow":"2","value":null}]
     * dlist : {"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/11/2016-07-07/577e710161490.jpg","like_image":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/04/2016-07-28/5799c429c9a84.jpg","nickname":"瑶瑶","signature":"不解风情的男人太多\u2026\u2026","sex":2,"birthday":"1991-11-12","star_sign":"","height":"165","weight":"60","salary":"20000以上","degree":"专科以下","birthplace":"","residence":"北京","profession":"媒体人","hobby":"萌宠","self_image":null,"ishouse":"有房","marital_status":"恋爱中","distance_love":"可以接受","like_sex":"落落大方","premarital_sex":"不能接受","raddress":null,"rcode":null,"rname":null,"t1":null,"t2":null,"t3":null}
     * baseinfo : {"nickname":"瑶瑶","sex":2,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/11/2016-07-07/577e710161490.jpg","like_image":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/04/2016-07-28/5799c429c9a84.jpg","profession":"媒体人","birthday":"1991-11-12","signature":"不解风情的男人太多\u2026\u2026","age":"25岁","vip_level":12,"isauth":"1","isfollow":"2"}
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
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/11/2016-07-07/577e710161490.jpg
         * like_image : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/04/2016-07-28/5799c429c9a84.jpg
         * nickname : 瑶瑶
         * signature : 不解风情的男人太多……
         * sex : 2
         * birthday : 1991-11-12
         * star_sign :
         * height : 165
         * weight : 60
         * salary : 20000以上
         * degree : 专科以下
         * birthplace :
         * residence : 北京
         * profession : 媒体人
         * hobby : 萌宠
         * self_image : null
         * ishouse : 有房
         * marital_status : 恋爱中
         * distance_love : 可以接受
         * like_sex : 落落大方
         * premarital_sex : 不能接受
         * raddress : null
         * rcode : null
         * rname : null
         * t1 : null
         * t2 : null
         * t3 : null
         */

        private DlistBean dlist;
        /**
         * nickname : 瑶瑶
         * sex : 2
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/11/2016-07-07/577e710161490.jpg
         * like_image : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/04/2016-07-28/5799c429c9a84.jpg
         * profession : 媒体人
         * birthday : 1991-11-12
         * signature : 不解风情的男人太多……
         * age : 25岁
         * vip_level : 12
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
         * field : like_image
         * name : 背景
         * edit : 1
         * isvip : 0
         * isshow : 1
         * value : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/04/2016-07-28/5799c429c9a84.jpg
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
            private String like_image;
            private String nickname;
            private String signature;
            private int sex;
            private String birthday;
            private String star_sign;
            private String height;
            private String weight;
            private String salary;
            private String degree;
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
            private String raddress;
            private String rcode;
            private String rname;
            private String t1;
            private String t2;
            private String t3;

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

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            public String getDegree() {
                return degree;
            }

            public void setDegree(String degree) {
                this.degree = degree;
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

            public String getRaddress() {
                return raddress;
            }

            public void setRaddress(String raddress) {
                this.raddress = raddress;
            }

            public String getRcode() {
                return rcode;
            }

            public void setRcode(String rcode) {
                this.rcode = rcode;
            }

            public String getRname() {
                return rname;
            }

            public void setRname(String rname) {
                this.rname = rname;
            }

            public String getT1() {
                return t1;
            }

            public void setT1(String t1) {
                this.t1 = t1;
            }

            public String getT2() {
                return t2;
            }

            public void setT2(String t2) {
                this.t2 = t2;
            }

            public String getT3() {
                return t3;
            }

            public void setT3(String t3) {
                this.t3 = t3;
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
            private String age;
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
            private String isshow;
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

            public String getIsshow() {
                return isshow;
            }

            public void setIsshow(String isshow) {
                this.isshow = isshow;
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
