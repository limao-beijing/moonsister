package com.hickey.network.bean;

/**
 * Created by x on 2016/9/6.
 */
public class personalBean extends BaseBean {

    /**
     * field : nickname
     * name : 昵称
     * edit : 1
     * isvip : 0
     * value : mm1471248477864
     */

    private String field;
    private String name;
    private String edit;
    private String isvip;
    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getIsvip() {
        return isvip;
    }

    public void setIsvip(String isvip) {
        this.isvip = isvip;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
