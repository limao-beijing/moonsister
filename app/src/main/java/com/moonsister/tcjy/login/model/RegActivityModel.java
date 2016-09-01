package com.moonsister.tcjy.login.model;

import android.os.UserManager;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.RegThridBean;

/**
 * Created by x on 2016/8/31.
 */
public interface RegActivityModel extends BaseIModel {
    void getThridReg(String mobile, String pwd, String birthday, String code,onLoadDateSingleListener<RegThridBean> listener);
    void getThridReg(String mobile,onLoadDateSingleListener<RegThridBean> listener);

}
