package com.moonsister.tcjy.login.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.LoginBean;

/**
 * Created by jb on 2016/6/17.
 */
public interface LoginFragmentModel  extends BaseIModel{
    void login(String phone, String password,onLoadDateSingleListener<LoginBean> Listener);

}
