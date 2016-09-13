package com.moonsister.tcjy.login.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.LoginBean;
import com.moonsister.tcjy.utils.MD5Util;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/6/17.
 */
public class LoginFragmentModelImpl implements LoginFragmentModel {
    @Override
    public void login(String phone, String password, onLoadDateSingleListener<LoginBean> Listener) {
        Observable<LoginBean> observable = ServerApi.getAppAPI().login(phone, MD5Util.string2MD5(password), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {

                Listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {

                Listener.onFailure(msg);
            }
        });
    }
}
