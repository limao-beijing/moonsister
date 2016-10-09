package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/9/10.
 */
public class PersonalReviseMessageBean extends BaseBean {


    /**
     * rules : [{"field":"smobile","name":"手机：","edit":"1","isvip":"1","isshow":"1","value":""},{"field":"qq","name":"QQ：","edit":"1","isvip":"1","isshow":"1","value":""},{"field":"weixin","name":"微信：","edit":"1","isvip":"1","isshow":"1","value":""},{"field":"face","name":"头像：","edit":"1","isvip":"0","isshow":"1","value":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160929/23/14751630532207228.jpg"},{"field":"like_image","name":"背景：","edit":"1","isvip":"0","isshow":"1","value":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160915/00/14738695991025620.jpg"},{"field":"nickname","name":"昵称：","edit":"1","isvip":"0","isshow":"1","value":"哈哈"},{"field":"signature","name":"个性签名：","edit":"1","isvip":"0","isshow":"1","value":"新人加入，请记得关注我哦。"},{"field":"sex","name":"性别：","edit":"2","isvip":"0","isshow":"1","value":"女"},{"field":"birthday","name":"出生年月：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"star_sign","name":"星座：","edit":"1","isvip":"0","isshow":"1","value":null},{"field":"height","name":"身高：","edit":"1","isvip":"0","isshow":"1","value":"0"},{"field":"weight","name":"体重：","edit":"1","isvip":"0","isshow":"1","value":"0"},{"field":"salary","name":"月薪：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"degree","name":"学历：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"birthplace","name":"籍贯：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"residence","name":"现居：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"profession","name":"职业：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"hobby","name":"兴趣爱好：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"self_image","name":"自我印象：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"ishouse","name":"是否有房：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"marital_status","name":"情感状态：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"distance_love","name":"接受异地恋：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"like_sex","name":"喜欢的异性：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"premarital_sex","name":"婚前性行为：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"raddress","name":"收货地址：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"rcode","name":"邮编：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"rname","name":"收货人：","edit":"1","isvip":"0","isshow":"1","value":""},{"field":"t1","name":"预留项1：","edit":"1","isvip":"0","isshow":"1","value":null},{"field":"t2","name":"预留项2：","edit":"1","isvip":"0","isshow":"2","value":null},{"field":"t3","name":"预留项3：","edit":"1","isvip":"0","isshow":"2","value":null}]
     * dlist : {"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160929/23/14751630532207228.jpg","like_image":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160915/00/14738695991025620.jpg","nickname":"哈哈","signature":"新人加入，请记得关注我哦。","sex":2,"birthday":"","star_sign":null,"height":"0","weight":"0","salary":"","degree":"","birthplace":"","residence":"","profession":"","hobby":"","self_image":"","ishouse":"","marital_status":"","distance_love":"","like_sex":"","premarital_sex":"","raddress":"","rcode":"","rname":"","t1":null,"t2":null,"t3":null}
     * baseinfo : {"nickname":"哈哈","sex":2,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160929/23/14751630532207228.jpg","like_image":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160915/00/14738695991025620.jpg","profession":"自由职业","birthday":"","signature":"新人加入，请记得关注我哦。","age":"22岁","vip_level":1,"isauth":"1","isfollow":"0","image_data":[{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160707/17/14678851443181755.jpg","s":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160707/17/14678851443181755.jpg"}],"image_video":[],"image_voice":[]}
     * vipinfo : {"smobile":"","qq":"","weixin":""}
     * ainfo : {"vip_level":1}
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
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160929/23/14751630532207228.jpg
         * like_image : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160915/00/14738695991025620.jpg
         * nickname : 哈哈
         * signature : 新人加入，请记得关注我哦。
         * sex : 2
         * birthday :
         * star_sign : null
         * height : 0
         * weight : 0
         * salary :
         * degree :
         * birthplace :
         * residence :
         * profession :
         * hobby :
         * self_image :
         * ishouse :
         * marital_status :
         * distance_love :
         * like_sex :
         * premarital_sex :
         * raddress :
         * rcode :
         * rname :
         * t1 : null
         * t2 : null
         * t3 : null
         */

        private DlistBean dlist;
        /**
         * nickname : 哈哈
         * sex : 2
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160929/23/14751630532207228.jpg
         * like_image : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160915/00/14738695991025620.jpg
         * profession : 自由职业
         * birthday :
         * signature : 新人加入，请记得关注我哦。
         * age : 22岁
         * vip_level : 1
         * isauth : 1
         * isfollow : 0
         * image_data : [{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160707/17/14678851443181755.jpg","s":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160707/17/14678851443181755.jpg"}]
         * image_video : []
         * image_voice : []
         */

        private BaseinfoBean baseinfo;
        /**
         * smobile :
         * qq :
         * weixin :
         */

        private VipinfoBean vipinfo;
        /**
         * vip_level : 1
         */

        private AinfoBean ainfo;
        /**
         * field : smobile
         * name : 手机：
         * edit : 1
         * isvip : 1
         * isshow : 1
         * value :
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

        public AinfoBean getAinfo() {
            return ainfo;
        }

        public void setAinfo(AinfoBean ainfo) {
            this.ainfo = ainfo;
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
            private Object star_sign;
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
            private Object t1;
            private Object t2;
            private Object t3;

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

            public Object getStar_sign() {
                return star_sign;
            }

            public void setStar_sign(Object star_sign) {
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

            public Object getT1() {
                return t1;
            }

            public void setT1(Object t1) {
                this.t1 = t1;
            }

            public Object getT2() {
                return t2;
            }

            public void setT2(Object t2) {
                this.t2 = t2;
            }

            public Object getT3() {
                return t3;
            }

            public void setT3(Object t3) {
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
            private int vip_level;
            private String isauth;
            private String isfollow;
            /**
             * l : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160707/17/14678851443181755.jpg
             * s : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160707/17/14678851443181755.jpg
             */

            private List<ImageDataBean> image_data;
            private List<ImageDataBean> image_video;
            private List<ImageDataBean> image_voice;

            public List<ImageDataBean> getImage_video() {
                return image_video;
            }

            public void setImage_video(List<ImageDataBean> image_video) {
                this.image_video = image_video;
            }

            public List<ImageDataBean> getImage_voice() {
                return image_voice;
            }

            public void setImage_voice(List<ImageDataBean> image_voice) {
                this.image_voice = image_voice;
            }

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

            public int getVip_level() {
                return vip_level;
            }

            public void setVip_level(int vip_level) {
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

            public List<ImageDataBean> getImage_data() {
                return image_data;
            }

            public void setImage_data(List<ImageDataBean> image_data) {
                this.image_data = image_data;
            }


            public static class ImageDataBean {
                private String l;
                private String s;

                public String getL() {
                    return l;
                }

                public void setL(String l) {
                    this.l = l;
                }

                public String getS() {
                    return s;
                }

                public void setS(String s) {
                    this.s = s;
                }
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

        public static class AinfoBean {
            private int vip_level;

            public int getVip_level() {
                return vip_level;
            }

            public void setVip_level(int vip_level) {
                this.vip_level = vip_level;
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
