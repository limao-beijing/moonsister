package com.moonsister.tcjy.main.view;


import com.hickey.tool.base.BaseIView;

/**
 * Created by pc on 2016/6/1.
 */
public interface MainView extends BaseIView {
    void switch2Home();

    void switch2IM();

    void switch2Center();

    void switchFind();

    void switch2My();

    void offline(String msg);
}
