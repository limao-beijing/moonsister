package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.PersonalMessageBean;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenterImpl;

/**
 * Created by x on 2016/8/29.
 */
public interface PersonalActivityModel extends BaseIModel {
    void loadData(int uid, onLoadDateSingleListener<PersonalMessageBean> listener);

    void userJson(String contents, onLoadDateSingleListener<PersonalMessageBean> listener);

}
