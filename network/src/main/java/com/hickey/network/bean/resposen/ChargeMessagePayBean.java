package com.hickey.network.bean.resposen;

/**
 * Created by jb on 2016/11/26.
 */
public class ChargeMessagePayBean extends BaseModel {
    private String abcode;
    private long expire_time;
    private String type;

    public String getAbcode() {
        return abcode;
    }

    public void setAbcode(String abcode) {
        this.abcode = abcode;
    }

    public long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(long expire_time) {
        this.expire_time = expire_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
