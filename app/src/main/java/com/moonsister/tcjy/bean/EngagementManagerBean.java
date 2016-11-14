package com.moonsister.tcjy.bean;

import com.google.gson.annotations.SerializedName;
import com.moonsister.tcjy.engagement.presenter.EngagementActionPersenterImpl;
import com.moonsister.tcjy.utils.EnumConstant;

import java.util.List;

/**
 * Created by jb on 2016/9/28.
 */
public class EngagementManagerBean extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        /**
         * id : 120
         * from_uid : 162043
         * to_uid : 161833
         * money : 1
         * order_id : 572016111456781
         * type : 2
         * date : 11月14号 18:08
         * address : jxjx
         * msg : 轰炸
         * create_time : 1479107321
         * status : 1
         * channel : null
         * dead_time : 1479193721
         * appeal_status : 0
         * deal_time : 0
         * ac_je_time : 0
         * isauto : 1
         * isvip : 0
         * mstatus : 1
         * dating_status_add : 1001
         * dating_id : 120
         * from_nickname : 费和平
         * from_sex : 1
         * from_face : http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png
         * vip_level : 12
         * accept_cdown : 1479366521
         * to_nickname : Nihao
         * to_sex : 2
         * to_face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20161026/16/40451477471245394.jpg
         * timeinfo : 2时39分
         * dating_status_add_msg : 等待应答
         */

        private String from_uid;
        private String to_uid;
        private String money;
        private String order_id;
        private int type;
        private String date;
        private String address;
        @SerializedName("msg")
        private String msgX;
        private int create_time;
        private int status;
        private Object channel;
        private long dead_time;
        private int appeal_status;
        private long deal_time;
        private long ac_je_time;
        private int isauto;
        private int isvip;
        private int mstatus;
        private int dating_status_add;
        private String dating_id;
        private String from_nickname;
        private int from_sex;
        private String from_face;
        private int vip_level;
        private long accept_cdown;
        private String to_nickname;
        private int to_sex;
        private String to_face;
        private String timeinfo;
        private String dating_status_add_msg;
        private EnumConstant.ManagerType mManagerType;
        private EngagementActionPersenterImpl mPresenetr;

        public EngagementActionPersenterImpl getPresenetr() {
            return mPresenetr;
        }

        public EnumConstant.ManagerType getManagerType() {
            return mManagerType;
        }

        public String getFrom_uid() {
            return from_uid;
        }

        public void setFrom_uid(String from_uid) {
            this.from_uid = from_uid;
        }

        public String getTo_uid() {
            return to_uid;
        }

        public void setTo_uid(String to_uid) {
            this.to_uid = to_uid;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMsgX() {
            return msgX;
        }

        public void setMsgX(String msgX) {
            this.msgX = msgX;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getChannel() {
            return channel;
        }

        public void setChannel(Object channel) {
            this.channel = channel;
        }

        public long getDead_time() {
            return dead_time;
        }

        public void setDead_time(long dead_time) {
            this.dead_time = dead_time;
        }

        public int getAppeal_status() {
            return appeal_status;
        }

        public void setAppeal_status(int appeal_status) {
            this.appeal_status = appeal_status;
        }

        public long getDeal_time() {
            return deal_time;
        }

        public void setDeal_time(long deal_time) {
            this.deal_time = deal_time;
        }

        public long getAc_je_time() {
            return ac_je_time;
        }

        public void setAc_je_time(long ac_je_time) {
            this.ac_je_time = ac_je_time;
        }

        public int getIsauto() {
            return isauto;
        }

        public void setIsauto(int isauto) {
            this.isauto = isauto;
        }

        public int getIsvip() {
            return isvip;
        }

        public void setIsvip(int isvip) {
            this.isvip = isvip;
        }

        public int getMstatus() {
            return mstatus;
        }

        public void setMstatus(int mstatus) {
            this.mstatus = mstatus;
        }

        public int getDating_status_add() {
            return dating_status_add;
        }

        public void setDating_status_add(int dating_status_add) {
            this.dating_status_add = dating_status_add;
        }

        public String getDating_id() {
            return dating_id;
        }

        public void setDating_id(String dating_id) {
            this.dating_id = dating_id;
        }

        public String getFrom_nickname() {
            return from_nickname;
        }

        public void setFrom_nickname(String from_nickname) {
            this.from_nickname = from_nickname;
        }

        public int getFrom_sex() {
            return from_sex;
        }

        public void setFrom_sex(int from_sex) {
            this.from_sex = from_sex;
        }

        public String getFrom_face() {
            return from_face;
        }

        public void setFrom_face(String from_face) {
            this.from_face = from_face;
        }

        public int getVip_level() {
            return vip_level;
        }

        public void setVip_level(int vip_level) {
            this.vip_level = vip_level;
        }

        public long getAccept_cdown() {
            return accept_cdown;
        }

        public void setAccept_cdown(long accept_cdown) {
            this.accept_cdown = accept_cdown;
        }

        public String getTo_nickname() {
            return to_nickname;
        }

        public void setTo_nickname(String to_nickname) {
            this.to_nickname = to_nickname;
        }

        public int getTo_sex() {
            return to_sex;
        }

        public void setTo_sex(int to_sex) {
            this.to_sex = to_sex;
        }

        public String getTo_face() {
            return to_face;
        }

        public void setTo_face(String to_face) {
            this.to_face = to_face;
        }

        public String getTimeinfo() {
            return timeinfo;
        }

        public void setTimeinfo(String timeinfo) {
            this.timeinfo = timeinfo;
        }

        public String getDating_status_add_msg() {
            return dating_status_add_msg;
        }

        public void setDating_status_add_msg(String dating_status_add_msg) {
            this.dating_status_add_msg = dating_status_add_msg;
        }

        public void setManagerType(EnumConstant.ManagerType managerType) {
            mManagerType = managerType;
        }

        public void setPresenetr(EngagementActionPersenterImpl presenetr) {
            mPresenetr = presenetr;
        }
    }
}
