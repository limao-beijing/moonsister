package com.moonsister.tcjy.utils;

/**
 * Created by jb on 2016/8/11.
 */
public class EnumConstant {
    /**
     * type 动态类型 1红包图集，2普通图文，3普通小视频动态，4免费语音，5付费语音，6付费视频
     *
     * @return
     */
    public enum DynamicType {
        CHARGE_PIC(1), FREE_PIC(2), FREE_VIDEO(3), FREE_VOICE(4), CHARGE_VOICE(5), CHARGE_VIDEO(6);


        private final int type;

        private DynamicType(int type) {
            this.type = type;
        }

        public int getValue() {
            return type;
        }
    }

    /**
     * 支付类型
     */
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

    /**
     * 首页类型
     */
    public enum HomeTopFragmentTop {
        HOT(1), NAERBY(2), NEW(3);
        private final int type;

        private HomeTopFragmentTop(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }
}
