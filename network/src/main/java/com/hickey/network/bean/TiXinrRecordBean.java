package com.hickey.network.bean;

import java.util.List;

/**
 * Created by jb on 2016/7/2.
 */
public class TiXinrRecordBean extends BaseBean {

    /**
     * code : 1
     * msg : 拉取成功
     * data : [{"id":1,"uid":144020,"money":"250","status":1,"create_time":1467440833,"audit_time":1467440833,"need_fee":1,"account_bank_name":"支付宝","account_bank_no":"kaixinpl@qq.com"},{"id":2,"uid":144020,"money":"500","status":1,"create_time":1467440833,"audit_time":1467440833,"need_fee":1,"account_bank_name":"支付宝","account_bank_no":"kaixinpl@qq.com"}]
     */


    /**
     * id : 1
     * uid : 144020
     * money : 250
     * status : 1
     * create_time : 1467440833
     * audit_time : 1467440833
     * need_fee : 1
     * account_bank_name : 支付宝
     * account_bank_no : kaixinpl@qq.com
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
        private String money;
        private long create_time;
        private long audit_time;
        private String need_fee;
        private String account_bank_name;
        private String account_bank_no;
        /**
         * status 提现状态 1申请中 2已通过 3不通过
         */
        private String status;
        /**
         *原因
         */
        private String remarks;

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public long getAudit_time() {
            return audit_time;
        }

        public void setAudit_time(long audit_time) {
            this.audit_time = audit_time;
        }

        public String getNeed_fee() {
            return need_fee;
        }

        public void setNeed_fee(String need_fee) {
            this.need_fee = need_fee;
        }

        public String getAccount_bank_name() {
            return account_bank_name;
        }

        public void setAccount_bank_name(String account_bank_name) {
            this.account_bank_name = account_bank_name;
        }

        public String getAccount_bank_no() {
            return account_bank_no;
        }

        public void setAccount_bank_no(String account_bank_no) {
            this.account_bank_no = account_bank_no;
        }
    }
}
