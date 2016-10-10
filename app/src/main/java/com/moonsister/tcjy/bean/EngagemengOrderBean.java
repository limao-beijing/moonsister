package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/9/27.
 */
public class EngagemengOrderBean extends BaseBean {

    /**
     * last_count : 0
     * dating_money : 80
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String last_count;
        private String dating_money;

        public String getLast_count() {
            return last_count;
        }

        public void setLast_count(String last_count) {
            this.last_count = last_count;
        }

        public String getDating_money() {
            return dating_money;
        }

        public void setDating_money(String dating_money) {
            this.dating_money = dating_money;
        }
    }
}
