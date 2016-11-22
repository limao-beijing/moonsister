package com.hickey.network.bean;

import java.util.List;

/**
 * Created by x on 2016/9/2.
 */
public class BalanceBean extends BaseBean{


    /**
     * desc : 对无忧进行打赏。
     * time : 2016-08-28|||21:27
     * money : 1.00
     * pic : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/03/2016-08-20/57b806ae4564b.jpg
     * balance_id : 14
     * act_uid : 104076
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
        private String time;
        private String money;
        private String pic;
        private String balance_id;
        private int act_uid;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
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

        public String getBalance_id() {
            return balance_id;
        }

        public void setBalance_id(String balance_id) {
            this.balance_id = balance_id;
        }

        public int getAct_uid() {
            return act_uid;
        }

        public void setAct_uid(int act_uid) {
            this.act_uid = act_uid;
        }
    }
}
