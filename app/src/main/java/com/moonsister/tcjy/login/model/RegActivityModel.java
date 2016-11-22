package com.moonsister.tcjy.login.model;

import com.hickey.network.bean.BaseBean;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by x on 2016/8/31.
 */
public interface RegActivityModel extends BaseIModel {
    void getThridReg(String mobile, String pwd, String birthday, String code,onLoadDateSingleListener<BaseBean> listener);
    void getSecurityCode(String mobile,onLoadDateSingleListener<BaseBean> listener);

}
