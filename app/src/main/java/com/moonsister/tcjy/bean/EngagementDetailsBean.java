package com.moonsister.tcjy.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jb on 2016/11/11.
 */
public class EngagementDetailsBean extends BaseBean {

    /**
     * data : {"status":1,"f_nickname":"方颖秀","f_face":"http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png","f_uid":162011,"t_nickname":"不吃胡萝卜的兔子","t_face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/12/2016-07-12/57846d473fa4f.jpg","t_uid":144094,"type":1,"date":"1478936520","msg":"还","address":"但","money":1,"order_id":"792016111214878","appeal_status":0,"daojishi":-27723,"dating_status_add":1001,"dating_status_add_msg":"等待应答"}
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
         * status : 1
         * f_nickname : 方颖秀
         * f_face : http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png
         * f_uid : 162011
         * t_nickname : 不吃胡萝卜的兔子
         * t_face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/12/2016-07-12/57846d473fa4f.jpg
         * t_uid : 144094
         * type : 1
         * date : 1478936520
         * msg : 还
         * address : 但
         * money : 1
         * order_id : 792016111214878
         * appeal_status : 0
         * daojishi : -27723
         * dating_status_add : 1001
         * dating_status_add_msg : 等待应答
         */

        private int status;
        private String f_nickname;
        private String f_face;
        private String f_uid;
        private String t_nickname;
        private String t_face;
        private String t_uid;
        private int type;
        private String date;
        @SerializedName("msg")
        private String msgX;
        private String address;
        private String money;
        private String order_id;
        private int appeal_status;
        private long daojishi;
        private int dating_status_add;
        private String dating_status_add_msg;
        private String info1;
        private String info2;
        private List<String> info3;
        private String dating_id ;


        public String getDating_id() {
            return dating_id;
        }

        public void setDating_id(String dating_id) {
            this.dating_id = dating_id;
        }

        public String getInfo1() {
            return info1;
        }

        public void setInfo1(String info1) {
            this.info1 = info1;
        }

        public String getInfo2() {
            return info2;
        }

        public void setInfo2(String info2) {
            this.info2 = info2;
        }

        public List<String> getInfo3() {
            return info3;
        }

        public void setInfo3(List<String> info3) {
            this.info3 = info3;
        }

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

        public int getAppeal_status() {
            return appeal_status;
        }

        public void setAppeal_status(int appeal_status) {
            this.appeal_status = appeal_status;
        }

        public long getDaojishi() {
            return daojishi;
        }

        public void setDaojishi(long daojishi) {
            this.daojishi = daojishi;
        }

        public int getDating_status_add() {
            return dating_status_add;
        }

        public void setDating_status_add(int dating_status_add) {
            this.dating_status_add = dating_status_add;
        }

        public String getDating_status_add_msg() {
            return dating_status_add_msg;
        }

        public void setDating_status_add_msg(String dating_status_add_msg) {
            this.dating_status_add_msg = dating_status_add_msg;
        }
    }
}
