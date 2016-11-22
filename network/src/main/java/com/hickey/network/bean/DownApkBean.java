package com.hickey.network.bean;

/**
 * Created by jb on 2016/9/15.
 */
public class DownApkBean extends BaseBean {
    /**
     * code : 1
     * msg : suc
     * data : {"url":"http://mimei.oss-cn-beijing.aliyuncs.com/download/duobao/com.immiao.yungou.apk","msg":"下载中，请稍后..."}
     */

    /**
     * url : http://mimei.oss-cn-beijing.aliyuncs.com/download/duobao/com.immiao.yungou.apk
     * msg : 下载中，请稍后...
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String url;
        private String msg;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
