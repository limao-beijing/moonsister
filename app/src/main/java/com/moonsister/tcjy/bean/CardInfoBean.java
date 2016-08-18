package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by jb on 2016/7/4.
 */
public class CardInfoBean extends BaseBean {


    /**
     * id : 16
     * name : 妈妈
     * type : 2
     * is_default : 0
     * uid : 144020
     * account_type : null
     * bank_name : 支付宝
     * bank_no : 123456
     * logo : https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=4215474172,2574253753&fm=58
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String name;
        private String type;
        private String is_default;
        private String uid;
        private Object account_type;
        private String bank_name;
        private String bank_no;
        private String logo;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public Object getAccount_type() {
            return account_type;
        }

        public void setAccount_type(Object account_type) {
            this.account_type = account_type;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_no() {
            return bank_no;
        }

        public void setBank_no(String bank_no) {
            this.bank_no = bank_no;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
