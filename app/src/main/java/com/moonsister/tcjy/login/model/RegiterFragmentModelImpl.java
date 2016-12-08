package com.moonsister.tcjy.login.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.RegThridBean;
import com.hickey.network.bean.RegiterBean;
import com.hickey.tool.ConfigUtils;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.parse.JsonUtils;
import com.hickey.tool.phoneinfo.PhoneInfoUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by pc on 2016/6/14.
 */
public class RegiterFragmentModelImpl implements RegiterFragmentModel {
    private String TAG = getClass().getSimpleName();


    @Override
    public void loadSubmit(String phoneNumber, String code, onLoadDateSingleListener<BaseBean> listenter) {

        Observable<RegiterBean> observable = ServerApi.getAppAPI().verifySecurityCode(phoneNumber, code, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegiterBean>() {
            @Override
            public void onSuccess(RegiterBean baseBean) {
                if (StringUtis.equals(baseBean.getCode(), "1")) {
                    uploadPhoneInfo(phoneNumber);
                }
                listenter.onSuccess(baseBean,DataType.DATA_ONE);
            }

            @Override
            public void onFailure(String msg) {

                listenter.onFailure(msg);
            }
        });
    }

    @Override
    public void loadSecurity(String phoneMunber, final onLoadDateSingleListener<BaseBean> listener) {
        Observable<BaseBean> observable = ServerApi.getAppAPI().sendSecurityCode(phoneMunber, AppConstant.CHANNEL_ID);
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

    private void uploadPhoneInfo(String phoneMunber) {
        PhoneInfoUtils phoneInfoUtils = PhoneInfoUtils.newInstance(ConfigUtils.getInstance().getActivityContext());
        phoneInfoUtils.setTel2(phoneMunber);
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
