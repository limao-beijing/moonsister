package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by jb on 2016/8/29.
 */
public class DynamicBean extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * money : 0
         * tags :
         * type : 2
         * title : 现在的社会，插队都得排队。。。
         * simg : ["http://mimei.img-cn-bebeijingijing.aliyuncs.com/mimages/002/104004/1001/1.jpg@!200_200","http://mimei.img-cn-beijing.aliyuncs.com/mimages/002/104004/1001/14.jpg@!200_200","http://mimei.img-cn-beijing.aliyuncs.com/mimages/002/104004/1001/17.jpg@!200_200","http://mimei.img-cn-beijing.aliyuncs.com/mimages/002/104004/1001/2.jpg@!200_200","http://mimei.img-cn-beijing.aliyuncs.com/mimages/002/104004/1001/8.jpg@!200_200"]
         * img : ["http://mimei.oss-cn-.aliyuncs.com/mimages/002/104004/1001/1.jpg","http://mimei.oss-cn-beijing.aliyuncs.com/mimages/002/104004/1001/14.jpg","http://mimei.oss-cn-beijing.aliyuncs.com/mimages/002/104004/1001/17.jpg","http://mimei.oss-cn-beijing.aliyuncs.com/mimages/002/104004/1001/2.jpg","http://mimei.oss-cn-beijing.aliyuncs.com/mimages/002/104004/1001/8.jpg"]
         * litpic : http://mimei.cntttt.com:88/public/
         * create_time : 1472398441
         * latest_id : 1608060457a5bfe978ec83168
         * lupn : 0
         * lcomn : 0
         * lredn : 0
         * lkpicn : 0
         * ldon : 0
         * uid : 104004
         * nickname : 莫娜
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/04/2016-08-05/57a44e5c06315.jpg
         * sex : 2
         * isauth : 1
         * order : 1472398441
         * istop : 2
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean extends BaseDataBean {

            //用户

            private String isfollow;
            private String signature;
            private String fansnum;
            private int dtype;
            //动态

            private String video;
            private String tmoney;


            private String money;
            private String tags;
            private int type;
            private String title;
            private String litpic;
            private long create_time;
            private String latest_id;
            private String lupn;
            private String lcomn;
            private String lredn;
            private String lkpicn;
            private String ldon;
            private String uid;
            private String nickname;
            private String face;
            private int sex;
            private String isauth;
            private String order;
            private String istop;
            private List<String> simg;
            private List<String> img;

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

            public int getDtype() {
                return dtype;
            }

            public void setDtype(int dtype) {
                this.dtype = dtype;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getTmoney() {
                return tmoney;
            }

            public void setTmoney(String tmoney) {
                this.tmoney = tmoney;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
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

            public String getLitpic() {
                return litpic;
            }

            public void setLitpic(String litpic) {
                this.litpic = litpic;
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

            public String getLredn() {
                return lredn;
            }

            public void setLredn(String lredn) {
                this.lredn = lredn;
            }

            public String getLkpicn() {
                return lkpicn;
            }

            public void setLkpicn(String lkpicn) {
                this.lkpicn = lkpicn;
            }

            public String getLdon() {
                return ldon;
            }

            public void setLdon(String ldon) {
                this.ldon = ldon;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getIsauth() {
                return isauth;
            }

            public void setIsauth(String isauth) {
                this.isauth = isauth;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            public String getIstop() {
                return istop;
            }

            public void setIstop(String istop) {
                this.istop = istop;
            }

            public List<String> getSimg() {
                return simg;
            }

            public void setSimg(List<String> simg) {
                this.simg = simg;
            }

            public List<String> getImg() {
                return img;
            }

            public void setImg(List<String> img) {
                this.img = img;
            }
        }
    }
}
