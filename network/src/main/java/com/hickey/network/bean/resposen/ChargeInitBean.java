package com.hickey.network.bean.resposen;

/**
 * Created by jb on 2016/11/28.
 */
public class ChargeInitBean extends BaseModel {
    private String fencheng_msg;
    private int chat_money_min;
    private int chat_money_max;

    public int getChat_money_max() {
        return chat_money_max;
    }

    public void setChat_money_max(int chat_money_max) {
        this.chat_money_max = chat_money_max;
    }

    public int getChat_money_min() {
        return chat_money_min;
    }

    public void setChat_money_min(int chat_money_min) {
        this.chat_money_min = chat_money_min;
    }

    public String getFencheng_msg() {
        return fencheng_msg;
    }

    public void setFencheng_msg(String fencheng_msg) {
        this.fencheng_msg = fencheng_msg;
    }
}
