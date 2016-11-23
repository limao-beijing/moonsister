package com.hickey.network.bean;

import java.util.List;

/**
 * Created by jb on 2016/11/23.
 */
public class ChargeMessageBean extends BaseBean{


    /**
     * data : {"source_id":"1","send_id":"2","pic":"http://manpingwang.com/uploadfiles/thumbs/20161113/5828835b818f9.jpg","friend_list":[]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * source_id : 1
         * send_id : 2
         * pic : http://manpingwang.com/uploadfiles/thumbs/20161113/5828835b818f9.jpg
         * friend_list : []
         */

        private String source_id;
        private String send_id;
        private String pic;
        private List<?> friend_list;

        public String getSource_id() {
            return source_id;
        }

        public void setSource_id(String source_id) {
            this.source_id = source_id;
        }

        public String getSend_id() {
            return send_id;
        }

        public void setSend_id(String send_id) {
            this.send_id = send_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public List<?> getFriend_list() {
            return friend_list;
        }

        public void setFriend_list(List<?> friend_list) {
            this.friend_list = friend_list;
        }
    }
}
