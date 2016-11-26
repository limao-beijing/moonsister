package com.moonsister.tcjy.main.model;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by pc on 2016/6/6.
 */
public interface DynamicModel {
    void loadUserInfoData(String userId, int page, BaseIModel.onLoadListDateListener<DynamicItemBean> listener);

    void loadUserInfodetail(String uid, BaseIModel.onLoadDateSingleListener listener);
}
