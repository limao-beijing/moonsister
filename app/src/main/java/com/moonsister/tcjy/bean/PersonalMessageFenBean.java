package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/9/21.
 */
public class PersonalMessageFenBean extends BaseBean {

    /**
     * rules : [{"field":"nickname","name":"昵称：","edit":"1","isvip":"0","isshow":"1","value":"json"},{"field":"signature","name":"个性签名：","edit":"1","isvip":"0","isshow":"1","value":"Uzbek"},{"field":"sex","name":"性别：","edit":"2","isvip":"0","isshow":"1","value":"男"},{"field":"birthday","name":"出生年月：","edit":"1","isvip":"0","isshow":"1","value":"1991-01-31"},{"field":"star_sign","name":"星座：","edit":"1","isvip":"0","isshow":"1","value":"宝瓶座"},{"field":"height","name":"身高：","edit":"1","isvip":"0","isshow":"1","value":"160"},{"field":"weight","name":"体重：","edit":"1","isvip":"0","isshow":"1","value":"57"},{"field":"salary","name":"月薪：","edit":"1","isvip":"0","isshow":"1","value":"10000\u201415000"},{"field":"degree","name":"学历：","edit":"1","isvip":"0","isshow":"1","value":"专科以下"},{"field":"birthplace","name":"籍贯：","edit":"1","isvip":"0","isshow":"1","value":"重庆"},{"field":"residence","name":"现居：","edit":"1","isvip":"0","isshow":"1","value":"成都"},{"field":"profession","name":"职业：","edit":"1","isvip":"0","isshow":"1","value":"主持"},{"field":"hobby","name":"兴趣爱好：","edit":"1","isvip":"0","isshow":"1","value":"游戏"},{"field":"self_image","name":"自我印象：","edit":"1","isvip":"0","isshow":"1","value":"负责任"},{"field":"ishouse","name":"是否有房：","edit":"1","isvip":"0","isshow":"1","value":"有房"},{"field":"marital_status","name":"情感状态：","edit":"1","isvip":"0","isshow":"1","value":"恋爱中"},{"field":"distance_love","name":"接受异地恋：","edit":"1","isvip":"0","isshow":"1","value":"看情况"},{"field":"like_sex","name":"喜欢的异性：","edit":"1","isvip":"0","isshow":"1","value":"小鸟依人"},{"field":"premarital_sex","name":"婚前性行为：","edit":"1","isvip":"0","isshow":"1","value":"看情况"}]
     * dlist : {"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160804/18/14703055089420276.jpg","like_image":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160812/13/14709792952357606.jpg","nickname":"json","signature":"Uzbek","sex":1,"birthday":"1991-01-31","star_sign":"宝瓶座","height":"160","weight":"57","salary":"10000\u201415000","degree":"专科以下","birthplace":"重庆","residence":"成都","profession":"主持","hobby":"游戏","self_image":"负责任","ishouse":"有房","marital_status":"恋爱中","distance_love":"看情况","like_sex":"小鸟依人","premarital_sex":"看情况","raddress":null,"rcode":null,"rname":null,"t1":null,"t2":null,"t3":null}
     * baseinfo : {"nickname":"json","sex":1,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160804/18/14703055089420276.jpg","like_image":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160812/13/14709792952357606.jpg","profession":"主持","birthday":"1991-01-31","signature":"Uzbek","age":"25岁","vip_level":0,"isauth":"1","isfollow":"2","image_data":"[{\"l\":\"http:\\/\\/mimei.oss-cn-beijing.aliyuncs.com\\/image\\/20160715\\/16\\/14685719449402490.jpg\",\"s\":\"\"},{\"l\":\"http:\\/\\/mimei.oss-cn-beijing.aliyuncs.com\\/image\\/20160715\\/16\\/14685719441527976.jpg\",\"s\":\"\"},{\"l\":\"http:\\/\\/mimei.oss-cn-beijing.aliyuncs.com\\/image\\/20160715\\/16\\/14685726820881173.jpg\",\"s\":\"\"}]","image_video":"{\"l\":\"http:\\/\\/mimei.oss-cn-beijing.aliyuncs.com\\/image\\/20160811\\/18\\/14709129297383161.jpg\",\"s\":\"http:\\/\\/mimei.oss-cn-beijing.aliyuncs.com\\/image\\/20160811\\/18\\/14709129300889004.jpg\",\"v\":\"http:\\/\\/mimei.oss-cn-beijing.aliyuncs.com\\/FREE_VIDEO\\/20160811\\/18\\/14709129209340802.mp4\"}","image_voice":"{\"l\":\"http:\\/\\/mimei.oss-cn-beijing.aliyuncs.com\\/default\\/voicebg\\/3.jpg\",\"s\":\"http:\\/\\/mimei.oss-cn-beijing.aliyuncs.com\\/default\\/voicebg\\/3.jpg\",\"v\":\"http:\\/\\/mimei.oss-cn-beijing.aliyuncs.com\\/VOICE\\/20160811\\/15\\/14709014657965662.amr\"}"}
     * vipinfo : {"smobile":null,"qq":null,"weixin":null}
     * ainfo : {"vip_level":"0"}
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
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160804/18/14703055089420276.jpg
         * like_image : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160812/13/14709792952357606.jpg
         * nickname : json
         * signature : Uzbek
         * sex : 1
         * birthday : 1991-01-31
         * star_sign : 宝瓶座
         * height : 160
         * weight : 57
         * salary : 10000—15000
         * degree : 专科以下
         * birthplace : 重庆
         * residence : 成都
         * profession : 主持
         * hobby : 游戏
         * self_image : 负责任
         * ishouse : 有房
         * marital_status : 恋爱中
         * distance_love : 看情况
         * like_sex : 小鸟依人
         * premarital_sex : 看情况
         * raddress : null
         * rcode : null
         * rname : null
         * t1 : null
         * t2 : null
         * t3 : null
         */

        private DlistBean dlist;
        /**
         * nickname : json
         * sex : 1
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160804/18/14703055089420276.jpg
         * like_image : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160812/13/14709792952357606.jpg
         * profession : 主持
         * birthday : 1991-01-31
         * signature : Uzbek
         * age : 25岁
         * vip_level : 0
         * isauth : 1
         * isfollow : 2
         * image_data : [{"l":"http:\/\/mimei.oss-cn-beijing.aliyuncs.com\/image\/20160715\/16\/14685719449402490.jpg","s":""},{"l":"http:\/\/mimei.oss-cn-beijing.aliyuncs.com\/image\/20160715\/16\/14685719441527976.jpg","s":""},{"l":"http:\/\/mimei.oss-cn-beijing.aliyuncs.com\/image\/20160715\/16\/14685726820881173.jpg","s":""}]
         * image_video : {"l":"http:\/\/mimei.oss-cn-beijing.aliyuncs.com\/image\/20160811\/18\/14709129297383161.jpg","s":"http:\/\/mimei.oss-cn-beijing.aliyuncs.com\/image\/20160811\/18\/14709129300889004.jpg","v":"http:\/\/mimei.oss-cn-beijing.aliyuncs.com\/FREE_VIDEO\/20160811\/18\/14709129209340802.mp4"}
         * image_voice : {"l":"http:\/\/mimei.oss-cn-beijing.aliyuncs.com\/default\/voicebg\/3.jpg","s":"http:\/\/mimei.oss-cn-beijing.aliyuncs.com\/default\/voicebg\/3.jpg","v":"http:\/\/mimei.oss-cn-beijing.aliyuncs.com\/VOICE\/20160811\/15\/14709014657965662.amr"}
         */

        private BaseinfoBean baseinfo;
        /**
         * smobile : null
         * qq : null
         * weixin : null
         */

        private VipinfoBean vipinfo;
        /**
         * vip_level : 0
         */

        private AinfoBean ainfo;
        /**
         * field : nickname
         * name : 昵称：
         * edit : 1
         * isvip : 0
         * isshow : 1
         * value : json
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
            private Object raddress;
            private Object rcode;
            private Object rname;
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

            public Object getRaddress() {
                return raddress;
            }

            public void setRaddress(Object raddress) {
                this.raddress = raddress;
            }

            public Object getRcode() {
                return rcode;
            }

            public void setRcode(Object rcode) {
                this.rcode = rcode;
            }

            public Object getRname() {
                return rname;
            }

            public void setRname(Object rname) {
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
            private String image_data;
            private String image_video;
            private String image_voice;

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

            public String getImage_data() {
                return image_data;
            }

            public void setImage_data(String image_data) {
                this.image_data = image_data;
            }

            public String getImage_video() {
                return image_video;
            }

            public void setImage_video(String image_video) {
                this.image_video = image_video;
            }

            public String getImage_voice() {
                return image_voice;
            }

            public void setImage_voice(String image_voice) {
                this.image_voice = image_voice;
            }
        }

        public static class VipinfoBean {
            private Object smobile;
            private Object qq;
            private Object weixin;

            public Object getSmobile() {
                return smobile;
            }

            public void setSmobile(Object smobile) {
                this.smobile = smobile;
            }

            public Object getQq() {
                return qq;
            }

            public void setQq(Object qq) {
                this.qq = qq;
            }

            public Object getWeixin() {
                return weixin;
            }

            public void setWeixin(Object weixin) {
                this.weixin = weixin;
            }
        }

        public static class AinfoBean {
            private String vip_level;

            public String getVip_level() {
                return vip_level;
            }

            public void setVip_level(String vip_level) {
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
