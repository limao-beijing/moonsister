package com.moonsister.tcjy.login.model;

import android.os.UserManager;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.RegThridBean;
import com.moonsister.tcjy.login.model.RegActivityModel;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.JsonUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.PhoneInfoUtils;

import rx.Observable;

/**
 * Created by x on 2016/8/31.
 */
public class RegActivityModelImpl implements RegActivityModel {

    @Override
    public void getThridReg(String mobile, String pwd, String birthday, String code, onLoadDateSingleListener<RegThridBean> listener) {
        Observable<RegThridBean> observable = ServerApi.getAppAPI().getRegThridBean(mobile,pwd,birthday,code, UserInfoManager.getInstance().getAuthcode(),AppConstant.API_VERSION, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegThridBean>() {
            @Override
            public void onSuccess(RegThridBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void getThridReg(String mobile, onLoadDateSingleListener<RegThridBean> listener) {
        uploadPhoneInfo(mobile);
        Observable<RegThridBean> observable = ServerApi.getAppAPI().sendSecurityCode(mobile, AppConstant.API_VERSION, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegThridBean>() {
            @Override
            public void onSuccess(RegThridBean baseBean) {
                listener.onSuccess(baseBean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {

                listener.onFailure(msg);
            }
        });
    }
    private void uploadPhoneInfo(String mobile) {
        PhoneInfoUtils phoneInfoUtils = PhoneInfoUtils.newInstance();
        phoneInfoUtils.setTel2(mobile);
        String serialize = JsonUtils.serialize(phoneInfoUtils);
        LogUtils.e(this, serialize);
        Observable<DefaultDataBean> observable = ServerApi.getAppAPI().getuploadPhoneInfo(serialize);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegThridBean>() {
            @Override
            public void onSuccess(RegThridBean bean) {

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
