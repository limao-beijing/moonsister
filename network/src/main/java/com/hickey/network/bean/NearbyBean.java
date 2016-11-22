package com.hickey.network.bean;

import java.util.List;

/**
 * Created by jb on 2016/8/4.
 */
public class NearbyBean extends BaseBean {

    /**
     * code : 1
     * msg : 拉取成功
     * data : [{"uid":144030,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160803/10/39311470191971679.jpg","nickname":"Sally","lat":"39.868989","lng":"116.504770"},{"uid":144139,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160722/17/14691804653475450.jpg","nickname":"黑哥","lat":"39.86875","lng":"116.50494"},{"uid":144026,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/11/2016-07-13/5785b1ec9c40e.jpg","nickname":"黑色幽默","lat":"39.868845","lng":"116.504867"},{"uid":144027,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160720/22/14690253242128453.jpg","nickname":"哈尼宝宝","lat":"39.869278","lng":"116.504616"},{"uid":144136,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160719/17/24491468920289199.jpg","nickname":"mm","lat":"39.86889","lng":"116.50483"},{"uid":144134,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160729/17/0831146978331151.jpg","nickname":"Snow","lat":"39.868926","lng":"116.504798"},{"uid":144157,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/public/face/moren.png","nickname":"mm1469518105668","lat":"39.870712","lng":"116.50415"},{"uid":104703,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/09/2016-07-07/577e5ff205679.png","nickname":"灵萧萧","lat":"39.870712","lng":"116.50415"},{"uid":104695,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160708/21/54101467986050173.jpg","nickname":"3","lat":"39.868835","lng":"116.504845"}]
     */
    /**
     * uid : 144030
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160803/10/39311470191971679.jpg
     * nickname : Sally
     * lat : 39.868989
     * lng : 116.504770
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String uid;
        private String face;
        private String nickname;
        private double lat;
        private double lng;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
