package com.hickey.network.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jb on 2016/7/14.
 */
public class PayBean {

    /**
     * appid : wxd73266bdf4679ebf
     * noncestr : dKih81nhPnA4sdQJW6M2DfbVYkvfiBCV
     * package : Sign=WXPay
     * partnerid : 1359350902
     * prepayid : wx2016071411545077391613ce0675263896
     * timestamp : 1468468490
     * sign : 9FBD701370FEDE23E7182D718F5C0545
     */
    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String type;
        //支付宝
        private String alicode;
        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String timestamp;
        private String sign;
        //爱贝
        private String abcode;
        //order_id
        private  String order_id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getAbcode() {
            return abcode;
        }

        public void setAbcode(String abcode) {
            this.abcode = abcode;
        }

        public String getAlicode() {
            return alicode;
        }

        public void setAlicode(String alicode) {
            this.alicode = alicode;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String setAbcode() {
            return null;
        }
    }
}
