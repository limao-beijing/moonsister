package com.hickey.tool.base;

/**
 * Created by pc on 2016/6/14.
 */
public interface BaseIView {
    /**
     * 显示加载行为
     */
    void showLoading();

    /**
     * 隐藏加载行为
     */
    void hideLoading();

    /**
     * 传递页面消息
     *
     * @param msg
     */
    void transfePageMsg(String msg);
}
