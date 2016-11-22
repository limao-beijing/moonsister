package com.moonsister.tcjy.find.model;

import com.hickey.network.bean.NearbyBean;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by jb on 2016/8/4.
 */
public interface NearbyActivityModel extends BaseIModel {
    void loadData(String sex, int page, onLoadListDateListener<NearbyBean.DataBean> listener);
}
