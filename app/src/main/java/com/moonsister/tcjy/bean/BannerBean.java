package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/9/9.
 */
public class BannerBean extends BaseBean {

    /**
     * type : 2
     * img : http://2.yytbzs.cn:88/static/ad/banner-top3.png
     * param : http://2test.yytbzs.cn:88/indextest.php/index/mmvip/info
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String type;
        private String img;
        private String param;
        private int width;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }
}
