package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;

/**
 * Created by jb on 2016/7/3.
 */
public interface AddCardActivityModel extends BaseIModel {
    void submitAccount(String cardNumber, String username, String cardType, String bankname, onLoadDateSingleListener listener);
}
