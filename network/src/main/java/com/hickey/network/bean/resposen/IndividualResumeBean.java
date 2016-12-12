package com.hickey.network.bean.resposen;

import java.util.List;

/**
 * Created by jb on 2016/12/1.
 */
public class IndividualResumeBean extends BaseModel {


    /**
     * code : 1
     * msg : succ
     * data : {"vip_level":"0","uid":450889,"smobile":null,"weixin":null,"qq":null,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png","nickname":"卜灵珊","sex":2,"profession":null,"signature":"新人加入，请记得关注我哦。","isauth":3,"age":"22岁","isfollow":"2","addinfo_a1":"TA，22岁，0cm，0kg","addinfo_a2":"籍贯，职业，现居，爱好，异地恋，，婚前性行为，","addinfo_a3":null,"image_data":[{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431071214575.jpg","s":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431071214575.jpg"},{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431064163932.jpg","s":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431064163932.jpg"},{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431056720642.jpg","s":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431056720642.jpg"}],"image_video":[{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431421954030.jpg","s":"http://mimei.img-cn-beijing.aliyuncs.com/image/20161210/12/14813431421954030.jpg@!336_246","v":"http://mimei.oss-cn-beijing.aliyuncs.com/FREE_VIDEO/20161210/12/14813431413771196.mp4"}],"image_voice":[{"l":"","s":"@!336_246","v":"http://mimei.oss-cn-beijing.aliyuncs.com/FREE_VOICE/20161210/12/14813431588653088.amr"}]}
     */


    /**
     * vip_level : 0
     * uid : 450889
     * smobile : null
     * weixin : null
     * qq : null
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png
     * nickname : 卜灵珊
     * sex : 2
     * profession : null
     * signature : 新人加入，请记得关注我哦。
     * isauth : 3
     * age : 22岁
     * isfollow : 2
     * addinfo_a1 : TA，22岁，0cm，0kg
     * addinfo_a2 : 籍贯，职业，现居，爱好，异地恋，，婚前性行为，
     * addinfo_a3 : null
     * image_data : [{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431071214575.jpg","s":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431071214575.jpg"},{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431064163932.jpg","s":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431064163932.jpg"},{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431056720642.jpg","s":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431056720642.jpg"}]
     * image_video : [{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20161210/12/14813431421954030.jpg","s":"http://mimei.img-cn-beijing.aliyuncs.com/image/20161210/12/14813431421954030.jpg@!336_246","v":"http://mimei.oss-cn-beijing.aliyuncs.com/FREE_VIDEO/20161210/12/14813431413771196.mp4"}]
     * image_voice : [{"l":"","s":"@!336_246","v":"http://mimei.oss-cn-beijing.aliyuncs.com/FREE_VOICE/20161210/12/14813431588653088.amr"}]
     */

    private int vip_level;
    private String uid;
    private String smobile;
    private String weixin;
    private String qq;
    private String face;
    private String nickname;
    private int sex;
    private String profession;
    private String signature;
    private int isauth;
    private String age;
    private String isfollow;
    private String addinfo_a1;
    private String addinfo_a2;
    private String addinfo_a3;
    private List<ImageDataBean> image_data;
    private List<ImageDataBean> image_video;
    private List<ImageDataBean> image_voice;

    public String getAddinfo_a1() {
        return addinfo_a1;
    }

    public void setAddinfo_a1(String addinfo_a1) {
        this.addinfo_a1 = addinfo_a1;
    }

    public String getAddinfo_a2() {
        return addinfo_a2;
    }

    public void setAddinfo_a2(String addinfo_a2) {
        this.addinfo_a2 = addinfo_a2;
    }

    public String getAddinfo_a3() {
        return addinfo_a3;
    }

    public void setAddinfo_a3(String addinfo_a3) {
        this.addinfo_a3 = addinfo_a3;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public List<ImageDataBean> getImage_data() {
        return image_data;
    }

    public void setImage_data(List<ImageDataBean> image_data) {
        this.image_data = image_data;
    }

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

    public int getIsauth() {
        return isauth;
    }

    public void setIsauth(int isauth) {
        this.isauth = isauth;
    }

    public String getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(String isfollow) {
        this.isfollow = isfollow;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSmobile() {
        return smobile;
    }

    public void setSmobile(String smobile) {
        this.smobile = smobile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getVip_level() {
        return vip_level;
    }

    public void setVip_level(int vip_level) {
        this.vip_level = vip_level;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public static class ImageDataBean {
        private String s;
        private String v;
        private String l;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public String getL() {
            return l;
        }

        public void setL(String l) {
            this.l = l;
        }
    }

}
