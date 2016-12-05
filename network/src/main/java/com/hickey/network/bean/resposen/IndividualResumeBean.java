package com.hickey.network.bean.resposen;

/**
 * Created by jb on 2016/12/1.
 */
public class IndividualResumeBean extends BaseModel {


    /**
     * vip_level : 12
     * uid : 154133
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/bimages/20160909/face/2253/ny41kf3ljjq2195.jpg
     * nickname : 米娅
     * sex : 2
     * profession : 策划
     * signature : 对整个世界来说，你是一个人，而对一个人来说，你就是整个世界。我会在这里等着你的到来，期待我们的爱情开花结果，期待把我们的小窝布置得浪漫温馨，一回到家就有幸福的感觉。
     * isauth : 3
     * addinfo_a1 : TA，25岁，176cm，59kg
     * addinfo_a2 : 籍贯广东,深圳，职业策划，现居上海市区，爱好写作，异地恋，不能接受，看情况婚前性行为，有房
     * addinfo_a3 : 胆大
     */

    private int vip_level;
    private String uid;
    private String face;
    private String nickname;
    private int sex;
    private String profession;
    private String signature;
    private int isauth;
    private String addinfo_a1;
    private String addinfo_a2;
    private String addinfo_a3;
    private String age;
    private String weixin;
    private String smobile;
    private String qq;
    private String isfollow;

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSmobile() {
        return smobile;
    }

    public void setSmobile(String smobile) {
        this.smobile = smobile;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public int getVip_level() {
        return vip_level;
    }

    public void setVip_level(int vip_level) {
        this.vip_level = vip_level;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public int getIsauth() {
        return isauth;
    }

    public void setIsauth(int isauth) {
        this.isauth = isauth;
    }

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

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {

        return age;
    }

    public String getFollow() {
        return isfollow;
    }

    public void setFollow(String follow) {
        this.isfollow = follow;
    }
}
