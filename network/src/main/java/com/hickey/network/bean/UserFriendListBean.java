package com.hickey.network.bean;

import java.util.List;

/**
 * Created by jb on 2016/8/18.
 */
public class UserFriendListBean extends BaseBean {

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}