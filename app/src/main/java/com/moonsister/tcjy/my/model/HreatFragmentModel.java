package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.UserDetailBean;
import com.moonsister.tcjy.my.persenter.HreatFragmentPresenterImpl;

/**
 * Created by x on 2016/9/8.
 */
public interface HreatFragmentModel extends BaseIModel {
//    void loadData(String uid, onLoadListDateListener listener);
    void loadData(String uid, onLoadDateSingleListener listener);
}
