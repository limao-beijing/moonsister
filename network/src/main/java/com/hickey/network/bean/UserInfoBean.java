package com.hickey.network.bean;

/**
 * Created by pc on 2016/6/6.
 */
public class UserInfoBean extends BaseDataBean {
    private String name;
    private int action;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
