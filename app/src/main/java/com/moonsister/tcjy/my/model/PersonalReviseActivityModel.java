package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;

/**
 * Created by x on 2016/9/10.
 */
public interface PersonalReviseActivityModel extends BaseIModel {
    void loadpersonalData(String uid, onLoadDateSingleListener<BaseBean> listener);

    void userJson(String contents, onLoadDateSingleListener<BaseBean> listener);
}
