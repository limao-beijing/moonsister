package com.moonsister.tcjy.my.persenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.PingbiView;

/**
 * Created by x on 2016/9/14.
 */
public interface PingbiActivityPersenter extends BaseIPresenter<PingbiView> {
    void submit(String page);
}
