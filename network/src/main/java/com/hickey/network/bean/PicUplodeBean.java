package com.hickey.network.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jb on 2016/6/23.
 */
public class PicUplodeBean implements Serializable {
    public List<DynamicContent> img;

    public List<DynamicContent> getImg() {
        return img;
    }

    public void setImg(List<DynamicContent> img) {
        this.img = img;
    }


}
