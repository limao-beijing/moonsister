package com.moonsister.tcjy.my.model;

import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by x on 2016/9/10.
 */
public interface PersonalReviseActivityModel extends BaseIModel {
    void loadpersonalData(String uid, onLoadDateSingleListener<BaseBean> listener);

    void userJson(String contents, onLoadDateSingleListener<BaseBean> listener);
}
