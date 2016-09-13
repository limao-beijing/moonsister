package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.PingbiBean;

/**
 * Created by x on 2016/9/14.
 */
public interface PingbiActivityModel extends BaseIModel {
    void loadData(String page,onLoadDateSingleListener<PingbiBean> listener);
}
