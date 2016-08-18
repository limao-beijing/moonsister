package com.moonsister.tcjy.bean;

import java.io.Serializable;

/**
 * Created by jb on 2016/6/23.
 */
public class DynamicContent implements Serializable {
    /**
     * 模糊图片
     */
    public String s;
    /**
     * 原图
     */
    public String l;
    
    private String v;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getV() {
        return v;
    }
}

