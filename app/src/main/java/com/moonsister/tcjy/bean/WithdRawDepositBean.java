package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/8/20.
 */
public class WithdRawDepositBean extends BaseBean {

    /**
     * withdraw_money : 3853.50
     * isfrozen : 0
     * frozen_money : 0
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

        public String getWithdraw_money() {
            return withdraw_money;
        }

        public void setWithdraw_money(String withdraw_money) {
            this.withdraw_money = withdraw_money;
        }

        public String getIsfrozen() {
            return isfrozen;
        }

        public void setIsfrozen(String isfrozen) {
            this.isfrozen = isfrozen;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }
    }
}
