package com.hickey.network.bean.resposen;

import java.util.ArrayList;

/**
 * Created by jb on 2016/11/28.
 */
public class ChargeResBean extends BaseModel {
    private ArrayList<String> cont;
    private String expire_sc;

    public ArrayList<String> getCont() {
        return cont;
    }

    public void setCont(ArrayList<String> cont) {
        this.cont = cont;
    }

    public String getExpire_sc() {
        return expire_sc;
    }

    public void setExpire_sc(String expire_sc) {
        this.expire_sc = expire_sc;
    }
}
