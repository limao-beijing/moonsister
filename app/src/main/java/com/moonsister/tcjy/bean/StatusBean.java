package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/9/28.
 */

public class StatusBean extends BaseBean {
    private String status;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
