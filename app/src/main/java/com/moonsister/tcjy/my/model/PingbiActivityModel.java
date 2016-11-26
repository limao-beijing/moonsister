package com.moonsister.tcjy.my.model;

import com.hickey.network.bean.PingbiBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by x on 2016/9/14.
 */
public interface PingbiActivityModel extends BaseIModel {
    void loadData(String page,onLoadDateSingleListener<PingbiBean> listener);
}
