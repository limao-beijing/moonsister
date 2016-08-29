package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.bean.UserInfoListBean;

/**
 * Created by pc on 2016/6/6.
 */
public interface DynamicModel {
    void loadUserInfoData(String userId, int page, BaseIModel.onLoadListDateListener<DynamicItemBean> listener);

    void loadUserInfodetail(String uid, BaseIModel.onLoadDateSingleListener listener);
}
