package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/9/2.
 */
public class BalanceBean extends BaseBean{

    /**
     * desc : 秋斯偷看了我的私密照片。
     * time : 1472750053
     * money : 1.00
     * pic : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/05/2016-08-18/57b57a2025622.jpg
     * balance_id : 626
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String desc;
        private int time;
        private String money;
        private String pic;
        private int balance_id;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getBalance_id() {
            return balance_id;
        }

        public void setBalance_id(int balance_id) {
            this.balance_id = balance_id;
        }
    }
}
