package com.moonsister.tcjy.login.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.RegThridBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tool.parse.JsonUtils;
import com.moonsister.tool.phoneinfo.PhoneInfoUtils;
import com.moonsister.tool.security.MD5Util;

import rx.Observable;

/**
 * Created by x on 2016/8/31.
 */
public class RegActivityModelImpl implements RegActivityModel {

    @Override
    public void getThridReg(String mobile, String pwd, String birthday, String code, onLoadDateSingleListener<BaseBean> listener) {
        PhoneInfoUtils phoneInfoUtils = PhoneInfoUtils.newInstance(ConfigUtils.getInstance().getActivityContext());
        phoneInfoUtils.setTel2(mobile);
        String serialize = JsonUtils.serialize(phoneInfoUtils);
        Observable<RegThridBean> observable = ServerApi.getAppAPI().getRegThridBean(mobile, MD5Util.string2MD5(pwd), birthday, code, serialize, UserInfoManager.getInstance().getAuthcode(), AppConstant.API_VERSION, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegThridBean>() {
            @Override
            public void onSuccess(RegThridBean bean) {
                listener.onSuccess(bean, DataType.DATA_ONE);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }


    @Override
    public void getSecurityCode(String mobile, onLoadDateSingleListener<BaseBean> listener) {
        uploadPhoneInfo(mobile);
        Observable<BaseBean> observable = ServerApi.getAppAPI().sendSecurityCode(mobile, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                listener.onSuccess(baseBean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    private void uploadPhoneInfo(String mobile) {
        PhoneInfoUtils phoneInfoUtils = PhoneInfoUtils.newInstance(ConfigUtils.getInstance().getActivityContext());
        phoneInfoUtils.setTel2(mobile);
        String serialize = JsonUtils.serialize(phoneInfoUtils);
        LogUtils.e(this, serialize);
        Observable<RegThridBean> observable = ServerApi.getAppAPI().getuploadPhoneInfo(AppConstant.CHANNEL_ID, UserInfoManager.getInstance().getAuthcode(), serialize);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
