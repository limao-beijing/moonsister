package com.hickey.network.bean.resposen;

import java.util.List;

/**
 * Created by jb on 2016/11/23.
 */
public class ChargeMessageBean extends BaseModel {


    /**
     * source_id : 1
     * send_id : 2
     * pic : http://manpingwang.com/uploadfiles/thumbs/20161113/5828835b818f9.jpg
     * friend_list : []
     */

    private String source_id;
    private String send_id;
    //过期时间
    private long expire_time;
    private String pic;
    private long size;
    private long sc;
    //播放时长
    private long expire_sc;
    private List<?> friend_list;

    public long getExpire_sc() {
        return expire_sc;
    }

    public void setExpire_sc(long expire_sc) {
        this.expire_sc = expire_sc;
    }

    public long getSc() {
        return sc;
    }

    public void setSc(long sc) {
        this.sc = sc;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(long expire_time) {
        this.expire_time = expire_time;
    }

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

