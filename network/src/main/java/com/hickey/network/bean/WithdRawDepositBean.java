package com.hickey.network.bean;

/**
 * Created by jb on 2016/8/20.
 */
public class WithdRawDepositBean extends BaseBean {


    /**
     * withdraw_money : 0
     * isfrozen : 0
     * frozen_money : 0
     * totoal_money : 0
     * last_money : 0.00
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String withdraw_money;
        private String isfrozen;
        private String frozen_money;
        private String totoal_money;
        private String last_money;

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getIsfrozen() {
            return isfrozen;
        }

        public void setIsfrozen(String isfrozen) {
            this.isfrozen = isfrozen;
        }

        public String getLast_money() {
            return last_money;
        }

        public void setLast_money(String last_money) {
            this.last_money = last_money;
        }

        public String getTotoal_money() {
            return totoal_money;
        }

        public void setTotoal_money(String totoal_money) {
            this.totoal_money = totoal_money;
        }

        public String getWithdraw_money() {
            return withdraw_money;
        }

        public void setWithdraw_money(String withdraw_money) {
            this.withdraw_money = withdraw_money;
        }
    }
}
