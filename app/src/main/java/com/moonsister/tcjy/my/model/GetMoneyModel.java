package com.moonsister.tcjy.my.model;


import com.hickey.tool.base.BaseIModel;

/**
 * Created by jb on 2016/7/3.
 */
public interface GetMoneyModel extends BaseIModel {
    void loadbasicInfo(onLoadDateSingleListener listener);

    void paySubmit(String number, int money, onLoadDateSingleListener listener);
}
