package com.moonsister.tcjy.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jb on 2016/6/29.
 */
public class PayRedPacketPicsBean extends BaseBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"simg":["http://mimei.img-cn-beijing.aliyuncs.com/image/20160628/20/14671166575394752.jpg@!200_200","http://mimei.img-cn-beijing.aliyuncs.com/image/20160628/20/14671166583119343.jpg@!200_200","http://mimei.img-cn-beijing.aliyuncs.com/image/20160628/20/14671166589453508.jpg@!200_200","http://mimei.img-cn-beijing.aliyuncs.com/image/20160628/20/14671166599613940.jpg@!200_200"],"img":["http://mimei.oss-cn-beijing.aliyuncs.com/image/20160628/20/14671166575394752.jpg","http://mimei.oss-cn-beijing.aliyuncs.com/image/20160628/20/14671166583119343.jpg","http://mimei.oss-cn-beijing.aliyuncs.com/image/20160628/20/14671166589453508.jpg","http://mimei.oss-cn-beijing.aliyuncs.com/image/20160628/20/14671166599613940.jpg"]}
     */


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> simg;
        private List<String> img;
        private String latest_id;
        private List<String> v;

        public List<String> getV() {
            return v;
        }

        public void setV(List<String> v) {
            this.v = v;
        }

        public String getLatest_id() {
            return latest_id;
        }

        public void setLatest_id(String latest_id) {
            this.latest_id = latest_id;
        }

        public List<String> getSimg() {
            return simg;
        }

        public void setSimg(List<String> simg) {
            this.simg = simg;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(ArrayList<String> img) {
            this.img = img;
        }
    }
}
