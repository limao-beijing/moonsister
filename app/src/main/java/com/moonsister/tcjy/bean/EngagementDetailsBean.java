package com.moonsister.tcjy.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jb on 2016/11/11.
 */
public class EngagementDetailsBean extends BaseBean {

    /**
     * data : {"f_nickname":"华翰采","f_face":"http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png","f_uid":161992,"t_nickname":"麦","t_face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/02/2016-07-12/578492210abdb.jpg","t_uid":144081,"type":3,"date":"1478849880","msg":"好想你在哪住呢你脑子","address":"杭州南站","money":10,"order_id":"912016111144059","daojishi":-9033}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean{
        /**
         * f_nickname : 华翰采
         * f_face : http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png
         * f_uid : 161992
         * t_nickname : 麦
         * t_face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/02/2016-07-12/578492210abdb.jpg
         * t_uid : 144081
         * type : 3
         * date : 1478849880
         * msg : 好想你在哪住呢你脑子
         * address : 杭州南站
         * money : 10
         * order_id : 912016111144059
         * daojishi : -9033
         */

        private String f_nickname;
        private String f_face;
        private String f_uid;
        private String t_nickname;
        private String t_face;
        private String t_uid;
        private int type;
        private int status;
        private String date;
        @SerializedName("msg")
        private String msgX;
        private String address;
        private String money;
        private String order_id;
        private long daojishi;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getF_nickname() {
            return f_nickname;
        }

        public void setF_nickname(String f_nickname) {
            this.f_nickname = f_nickname;
        }

        public String getF_face() {
            return f_face;
        }

        public void setF_face(String f_face) {
            this.f_face = f_face;
        }

        public String getF_uid() {
            return f_uid;
        }

        public void setF_uid(String f_uid) {
            this.f_uid = f_uid;
        }

        public String getT_nickname() {
            return t_nickname;
        }

        public void setT_nickname(String t_nickname) {
            this.t_nickname = t_nickname;
        }

        public String getT_face() {
            return t_face;
        }

        public void setT_face(String t_face) {
            this.t_face = t_face;
        }

        public String getT_uid() {
            return t_uid;
        }

        public void setT_uid(String t_uid) {
            this.t_uid = t_uid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMsgX() {
            return msgX;
        }

        public void setMsgX(String msgX) {
            this.msgX = msgX;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public long getDaojishi() {
            return daojishi;
        }

        public void setDaojishi(long daojishi) {
            this.daojishi = daojishi;
        }
    }
}
