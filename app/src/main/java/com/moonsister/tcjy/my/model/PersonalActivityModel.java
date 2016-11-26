package com.moonsister.tcjy.my.model;

import com.hickey.network.bean.PersonalMessageBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by x on 2016/8/29.
 */
public interface PersonalActivityModel extends BaseIModel {
    void loadData(String uid, onLoadDateSingleListener<PersonalMessageBean> listener);

}
