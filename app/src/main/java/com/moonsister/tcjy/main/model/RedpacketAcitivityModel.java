package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;

/**
 * Created by jb on 2016/6/24.
 */
public interface RedpacketAcitivityModel extends BaseIModel {
    void pay(int type, PayType playType, String uid, String money, onLoadDateSingleListener listener);

    public enum PayType {
        ALI_PAY("alipay"), WX_PAY("wxpay"), IAPP_PAY("iapppay");
        private final String type;

        private PayType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}

