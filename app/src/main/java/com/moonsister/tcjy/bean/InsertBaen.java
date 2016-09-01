package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/8/27.
 */
public class InsertBaen extends BaseBean{


    /**
     * tagid : 18
     * tagname : 惊世才艺
     * img : http://mimei.oss-cn-beijing.aliyuncs.com/default/xingqu/jingshicaiyi.jpg
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int tagid;
        private String tagname;
        private String img;
        boolean ischeck;

        public boolean ischeck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public int getTagid() {
            return tagid;
        }

        public void setTagid(int tagid) {
            this.tagid = tagid;
        }

        public String getTagname() {
            return tagname;
        }

        public void setTagname(String tagname) {
            this.tagname = tagname;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }


    }
}
