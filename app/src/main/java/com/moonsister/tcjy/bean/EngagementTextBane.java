package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/11/11.
 */
public class EngagementTextBane extends BaseBean {

    /**
     * data : {"info":"1.约见成功，赏金归对方所有；\n2.约见不成功，普通会员无法享受担保约见服务，建议您升级为VIP会员【立即开通VIP】，VIP会员可在48小时内申请退款，申请后赏金全额退还；\n3.约见成功后，赏金将在对方账户冻结48小时，若期间发现信息不符，可申述官方担保全额退款；\n4.约见成功后，对方有48小时回应时间（若距预定时间不足48小时则以实际时间计算），超48小时未回应，全额退款。"}
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
         * info : 1.约见成功，赏金归对方所有；
         2.约见不成功，普通会员无法享受担保约见服务，建议您升级为VIP会员【立即开通VIP】，VIP会员可在48小时内申请退款，申请后赏金全额退还；
         3.约见成功后，赏金将在对方账户冻结48小时，若期间发现信息不符，可申述官方担保全额退款；
         4.约见成功后，对方有48小时回应时间（若距预定时间不足48小时则以实际时间计算），超48小时未回应，全额退款。
         */

        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
