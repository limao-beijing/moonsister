package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;

/**
 * Created by x on 2016/9/8.
 */
public interface HreatFragmentModel extends BaseIModel {
//    void loadByIdData(String uid, onLoadListDateListener listener);
    void loadData(String uid, onLoadDateSingleListener listener);
}
