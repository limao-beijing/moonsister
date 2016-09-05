package com.moonsister.tcjy.bean;

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
        private int withdraw_money;
        private int isfrozen;
        private String frozen_money;
        private int totoal_money;
        private String last_money;

        public int getWithdraw_money() {
            return withdraw_money;
        }

        public void setWithdraw_money(int withdraw_money) {
            this.withdraw_money = withdraw_money;
        }

        public int getIsfrozen() {
            return isfrozen;
        }

        public void setIsfrozen(int isfrozen) {
            this.isfrozen = isfrozen;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public int getTotoal_money() {
            return totoal_money;
        }

        public void setTotoal_money(int totoal_money) {
            this.totoal_money = totoal_money;
        }

        public String getLast_money() {
            return last_money;
        }

        public void setLast_money(String last_money) {
            this.last_money = last_money;
        }
    }
}
