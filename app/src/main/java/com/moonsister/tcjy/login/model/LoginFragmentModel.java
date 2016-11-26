package com.moonsister.tcjy.login.model;

import com.hickey.network.bean.LoginBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/6/17.
 */
public interface LoginFragmentModel  extends BaseIModel {
    void login(String phone, String password,onLoadDateSingleListener<LoginBean> Listener);

}
