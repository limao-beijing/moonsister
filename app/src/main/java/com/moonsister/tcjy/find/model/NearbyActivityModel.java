package com.moonsister.tcjy.find.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.NearbyBean;

/**
 * Created by jb on 2016/8/4.
 */
public interface NearbyActivityModel extends BaseIModel {
    void loadData(String sex, int page, onLoadListDateListener<NearbyBean.DataBean> listener);
}
