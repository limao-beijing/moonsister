package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by jb on 2016/6/22.
 */
public class CommentDataListBean extends BaseBean {

    /**
     * id : 16070720577f2356dc66f754318
     * lid : 16070720577e13631ddf37448
     * uid : 144030
     * title : Hahaha
     * create_time : 1467949910
     * pid : 0
     * status : 1
     * nickname : mm1467876353892
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160707/15/25451467876345167.jpg
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String lid;
        private String uid;
        private String title;
        private long create_time;
        private String pid;
        private String status;
        private String nickname;
        private String face;

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
    }
}
