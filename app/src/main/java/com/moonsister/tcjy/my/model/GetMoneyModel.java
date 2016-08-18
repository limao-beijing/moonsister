package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;

/**
 * Created by jb on 2016/7/3.
 */
public interface GetMoneyModel extends BaseIModel {
    void loadbasicInfo(onLoadDateSingleListener listener);

    void paySubmit(String number, int money, onLoadDateSingleListener listener);
}
