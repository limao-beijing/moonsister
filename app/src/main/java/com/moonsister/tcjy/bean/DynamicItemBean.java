package com.moonsister.tcjy.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jb on 2016/8/29.
 */
public class DynamicItemBean extends BaseDataBean {


    //用户
    private String age;
    private String isfollow;
    private String signature;
    private String fansnum;
    private int dtype;
    //动态
    private String tags;
    private String lkpicn;
    private ArrayList<String> img;
    private long create_time;
    private String latest_id;
    private List<String> simg;
    private String lupn;
    private String lcomn;
    private String litpic;
    private String title;
    private int type;
    private String lredn;
    private String vimg;
    private String face;
    private String nickname;
    private String sex;
    private String uid;
    private String money;
    private String video;
    private String tmoney;
    private String comment_count;
    private String view_num;
    private String ldon;
    //1认证  0 未认证
    private String isauth;
    //置顶
    private String istop;
    /**
     * 1 已支付  2 未支付
     */


    private String ispay;


    public int getDtype() {
        return dtype;
    }

    public String getIspay() {
        return ispay;
    }

    public void setIspay(String ispay) {
        this.ispay = ispay;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public void setDtype(int dtype) {
        this.dtype = dtype;
    }

    public String getFansnum() {
        return fansnum;
    }

    public void setFansnum(String fansnum) {
        this.fansnum = fansnum;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(String isfollow) {
        this.isfollow = isfollow;
    }

    public String getLkpicn() {
        return lkpicn;
    }

    public void setLkpicn(String lkpicn) {
        this.lkpicn = lkpicn;
    }

    public ArrayList<String> getImg() {
        return img;
    }

    public void setImg(ArrayList<String> img) {
        this.img = img;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getLatest_id() {
        return latest_id;
    }

    public void setLatest_id(String latest_id) {
        this.latest_id = latest_id;
    }

    public List<String> getSimg() {
        return simg;
    }

    public void setSimg(List<String> simg) {
        this.simg = simg;
    }

    public String getLupn() {
        return lupn;
    }

    public void setLupn(String lupn) {
        this.lupn = lupn;
    }

    public String getLcomn() {
        return lcomn;
    }

    public void setLcomn(String lcomn) {
        this.lcomn = lcomn;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLredn() {
        return lredn;
    }

    public void setLredn(String lredn) {
        this.lredn = lredn;
    }

    public String getVimg() {
        return vimg;
    }

    public void setVimg(String vimg) {
        this.vimg = vimg;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTmoney() {
        return tmoney;
    }

    public void setTmoney(String tmoney) {
        this.tmoney = tmoney;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getIsauth() {
        return isauth;
    }

    public void setIsauth(String isauth) {
        this.isauth = isauth;
    }

    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getView_num() {
        return view_num;
    }

    public void setView_num(String view_num) {
        this.view_num = view_num;
    }

    public String getLdon() {
        return ldon;
    }

    public void setLdon(String ldon) {
        this.ldon = ldon;
    }
}
