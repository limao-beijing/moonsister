package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by x on 2016/9/3.
 */
public interface RechargeActivityModel extends BaseIModel {
    void loadData(String money, String pay_type, onLoadDateSingleListener listener);

}
