package com.hickey.network.bean;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public class HomeThreeFragmentBean extends BaseBean {


    /**
     * uid : 145961
     * nickname : 洛烟
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/05/2016-08-18/57b57eb983ef2.jpg
     * signature : 那生锈的课桌上还刻有他的名字吗
     * weight : 59
     * height : 178cm
     * qq : null
     * weixin : null
     * smobile : null
     * age : 22岁
     * vip_level : 1
     * lat : 0
     * lng : 0
     * showlist : [{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/mimages/005/145961/2010/16-71-2小妖(77).jpg","s":"http://mimei.img-cn-beijing.aliyuncs.com/mimages/005/145961/2010/16-71-2小妖(77).jpg@!336_246"},{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/mimages/005/145961/2010/16-71-2小妖(78).jpg","s":"http://mimei.img-cn-beijing.aliyuncs.com/mimages/005/145961/2010/16-71-2小妖(78).jpg@!336_246"},{"l":"http://mimei.oss-cn-beijing.aliyuncs.com/mimages/005/145961/2010/16-71-2小妖(79).jpg","s":"http://mimei.img-cn-beijing.aliyuncs.com/mimages/005/145961/2010/16-71-2小妖(79).jpg@!336_246"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String uid;
        private String nickname;
        private String face;
        private String signature;
        private String weight;
        private String height;
        private String qq;
        private String weixin;
        private String smobile;
        private String age;
        private String vip_level;
        private String lat;
        private String lng;
        private String distance;
        private String distance_love;

        public String getDistance_love() {
            return distance_love;
        }

        public void setDistance_love(String distance_love) {
            this.distance_love = distance_love;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        /**
         * l : http://mimei.oss-cn-beijing.aliyuncs.com/mimages/005/145961/2010/16-71-2小妖(77).jpg
         * s : http://mimei.img-cn-beijing.aliyuncs.com/mimages/005/145961/2010/16-71-2小妖(77).jpg@!336_246
         */

        private List<ShowlistBean> showlist;

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

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }


        public String getAge() {
            return age;
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

        public String getSmobile() {
            return smobile;
        }

        public void setSmobile(String smobile) {
            this.smobile = smobile;
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

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public List<ShowlistBean> getShowlist() {
            return showlist;
        }

        public void setShowlist(List<ShowlistBean> showlist) {
            this.showlist = showlist;
        }

        public static class ShowlistBean {
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
}
