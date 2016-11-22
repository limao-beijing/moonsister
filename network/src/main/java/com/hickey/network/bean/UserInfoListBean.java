package com.hickey.network.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfoListBean extends BaseBean {

    private UserInfoListBeanData data;


    public UserInfoListBeanData getData() {
        return this.data;
    }

    public void setData(UserInfoListBeanData data) {
        this.data = data;
    }

    public static class UserInfoListBeanData {
        private List<DynamicItemBean> list;

        public List<DynamicItemBean> getList() {
            return this.list;
        }

        public void setList(List<DynamicItemBean> list) {
            this.list = list;
        }

        public static class UserInfoListBeanDataList extends BaseDataBean {


            //用户

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
            //1认证  0 未认证
            private String isauth;
            //置顶
            private String istop;

            public String getIsfollow() {
                return isfollow;
            }

            public void setIsfollow(String isfollow) {
                this.isfollow = isfollow;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
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

            public int getDtype() {
                return dtype;
            }

            public void setDtype(int dtype) {
                this.dtype = dtype;
            }

            /**
             * 1 已支付  2 未支付
             */


            private String ispay;

            public String getIstop() {
                return istop;
            }

            public void setIstop(String istop) {
                this.istop = istop;
            }

            public String getIsauth() {
                return isauth;
            }

            public void setIsauth(String isauth) {
                this.isauth = isauth;
            }

            public String getTmoney() {
                return tmoney;
            }

            public void setTmoney(String tmoney) {
                this.tmoney = tmoney;
            }

            public String getIspay() {
                return ispay;
            }

            public void setIspay(String ispay) {
                this.ispay = ispay;
            }


            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
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

            public List<String> getSimg() {
                return simg;
            }

            public void setSimg(List<String> simg) {
                this.simg = simg;
            }

            public String getLatest_id() {
                return latest_id;
            }

            public void setLatest_id(String latest_id) {
                this.latest_id = latest_id;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getVimg() {
                return vimg;
            }

            public void setVimg(String vimg) {
                this.vimg = vimg;
            }

            public String getLredn() {
                return lredn;
            }

            public void setLredn(String lredn) {
                this.lredn = lredn;
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

            public static class Simg implements Serializable {
                private String url;

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUrl() {
                    return this.url;
                }

            }

            public static class Img implements Serializable {
                private String url;

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUrl() {
                    return this.url;
                }

            }


        }
    }
}
