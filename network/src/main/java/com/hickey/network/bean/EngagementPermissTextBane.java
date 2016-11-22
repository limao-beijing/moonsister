package com.hickey.network.bean;

/**
 * Created by jb on 2016/11/14.
 */
public class EngagementPermissTextBane  extends BaseBean{

    /**
     * data : {"info":"VIP充值等级：\n体验VIP：29元/3天 【5条付费视频价格】\n初级VIP：59元/1个月【12条付费视频价格】\n中级VIP：129元/3个月【25条付费视频价格】\n高级VIP：229元/12个月【45条付费视频价格】\n终身VIP：509元/终身 【100条付费视频价格】","top_info":"你还不是VIP，需要支付担保约见赏金，约见成功不享受官方担保，约见不成功无法申请退款。"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * info : VIP充值等级：
         体验VIP：29元/3天 【5条付费视频价格】
         初级VIP：59元/1个月【12条付费视频价格】
         中级VIP：129元/3个月【25条付费视频价格】
         高级VIP：229元/12个月【45条付费视频价格】
         终身VIP：509元/终身 【100条付费视频价格】
         * top_info : 你还不是VIP，需要支付担保约见赏金，约见成功不享受官方担保，约见不成功无法申请退款。
         */

        private String info;
        private String top_info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getTop_info() {
            return top_info;
        }

        public void setTop_info(String top_info) {
            this.top_info = top_info;
        }
    }
}
