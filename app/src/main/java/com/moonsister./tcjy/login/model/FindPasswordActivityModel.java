package com.moonsister.tcjy.login.model;

import com.moonsister.tcjy.base.BaseIModel;

/**
 * Created by jb on 2016/7/11.
 */
public interface FindPasswordActivityModel extends BaseIModel {
    void getSecurityCode(String phoneNumber, onLoadDateSingleListener listener);

    void submit(String phone, String code, onLoadDateSingleListener listenter);
}
