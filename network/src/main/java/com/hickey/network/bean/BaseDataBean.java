package com.hickey.network.bean;

import java.io.Serializable;

/**
 * Created by jb on 2016/6/17.
 */
public class BaseDataBean implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
