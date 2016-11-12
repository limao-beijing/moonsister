package com.moonsister.tcjy.bean;

import com.moonsister.tcjy.engagement.presenter.EngagementActionPersenter;
import com.moonsister.tcjy.utils.EnumConstant;

import java.util.List;

/**
 * Created by jb on 2016/9/28.
 */
public class EngagementManagerBean extends BaseBean {


    /**
     * id : 12
     * from_uid : 156794
     * to_uid : 145982
     * money : 1000
     * order_id : 0
     * type : 1
     * date : 2016-10-03 17:30
     * address : 湖南省长沙市雨花区xxx街
     * msg : 我来了，你在哪儿13
     * create_time : 1475030396
     * status : 1
     * channel : null
     * dead_time : 0
     * date_status : 2
     * appeal_dead_time : 0
     * deal_time : 0
     * ac_je_time : 0
     * appeal_time : 0
     * from_nickname : 哈哈
     * from_sex : 2
     * from_face : http://mimei.cntttt.com:88/public/
     * vip_level : 1
     * to_nickname : mm61080908
     * to_sex : 1
     * to_face : http://mimei.cntttt.com:88/public/
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String from_uid;
        private String to_uid;
        private String money;
        private String order_id;
        private int type;
        private String date;
        private String address;
        private String msg;
        private long create_time;
        private int status;
        private String channel;
        private long dead_time;
        private String date_status;
        private long appeal_dead_time;
        private long deal_time;
        private long ac_je_time;
        private long appeal_time;
        private String from_nickname;
        private String from_sex;
        private String from_face;
        private String vip_level;
        private String to_nickname;
        private String to_sex;
        private String to_face;
        private String accept_cdown;
        private String timeinfo;
        //0未申诉，1申诉中，2已成功（约会状态同步为4已失败）
        private int appeal_status;
        private EnumConstant.ManagerType mType;
        private EngagementActionPersenter mPresenetr;

        public EngagementActionPersenter getPresenetr() {
            return mPresenetr;
        }

        public String getTimeinfo() {
            return timeinfo;
        }

        public void setTimeinfo(String timeinfo) {
            this.timeinfo = timeinfo;
        }

        public void setType(EnumConstant.ManagerType type) {
            mType = type;
        }

        public void setManagerType(EnumConstant.ManagerType type) {
            mType = type;
        }

        public EnumConstant.ManagerType getManagerType() {
            return mType;
        }

        public int getAppeal_status() {
            return appeal_status;
        }

        public void setAppeal_status(int appeal_status) {
            this.appeal_status = appeal_status;
        }

        public String getAccept_cdown() {
            return accept_cdown;
        }

        public void setAccept_cdown(String accept_cdown) {
            this.accept_cdown = accept_cdown;
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

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public long getDead_time() {
            return dead_time;
        }

        public void setDead_time(long dead_time) {
            this.dead_time = dead_time;
        }

        public String getDate_status() {
            return date_status;
        }

        public void setDate_status(String date_status) {
            this.date_status = date_status;
        }

        public long getAppeal_dead_time() {
            return appeal_dead_time;
        }

        public void setAppeal_dead_time(long appeal_dead_time) {
            this.appeal_dead_time = appeal_dead_time;
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

        public long getAppeal_time() {
            return appeal_time;
        }

        public void setAppeal_time(long appeal_time) {
            this.appeal_time = appeal_time;
        }

        public String getFrom_nickname() {
            return from_nickname;
        }

        public void setFrom_nickname(String from_nickname) {
            this.from_nickname = from_nickname;
        }

        public String getFrom_sex() {
            return from_sex;
        }

        public void setFrom_sex(String from_sex) {
            this.from_sex = from_sex;
        }

        public String getFrom_face() {
            return from_face;
        }

        public void setFrom_face(String from_face) {
            this.from_face = from_face;
        }

        public String getVip_level() {
            return vip_level;
        }

        public void setVip_level(String vip_level) {
            this.vip_level = vip_level;
        }

        public String getTo_nickname() {
            return to_nickname;
        }

        public void setTo_nickname(String to_nickname) {
            this.to_nickname = to_nickname;
        }

        public String getTo_sex() {
            return to_sex;
        }

        public void setTo_sex(String to_sex) {
            this.to_sex = to_sex;
        }

        public String getTo_face() {
            return to_face;
        }

        public void setTo_face(String to_face) {
            this.to_face = to_face;
        }

        public void setPresenetr(EngagementActionPersenter presenetr) {
            mPresenetr = presenetr;
        }
    }
}
