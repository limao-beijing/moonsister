package com.hickey.network.bean;

/**
 * Created by x on 2016/9/3.
 */
public class BackTermsBean extends BaseBean{


    /**
     * voice_info : 飘飘欲仙
     * uid : 145655
     * nickname : mm1471248477864
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png
     * mobile : 15010838669
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String voice_info;
        private int uid;
        private String nickname;
        private String face;
        private String mobile;

        public String getVoice_info() {
            return voice_info;
        }

        public void setVoice_info(String voice_info) {
            this.voice_info = voice_info;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
