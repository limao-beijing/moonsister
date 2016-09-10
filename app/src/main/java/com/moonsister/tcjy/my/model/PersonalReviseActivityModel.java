package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.PersonalReviseMessageBean;

/**
 * Created by x on 2016/9/10.
 */
public interface PersonalReviseActivityModel extends BaseIModel {
    void loadpersonalData(int uid,onLoadDateSingleListener<PersonalReviseMessageBean> listener);
}
