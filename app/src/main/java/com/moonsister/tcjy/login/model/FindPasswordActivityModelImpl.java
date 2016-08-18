package com.moonsister.tcjy.login.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.RegiterBean;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/7/11.
 */
public class FindPasswordActivityModelImpl implements FindPasswordActivityModel {
    @Override
    public void getSecurityCode(String phoneNumber, onLoadDateSingleListener listener) {
        Observable<BaseBean> observable = ServerApi.getAppAPI().getSecurityCode(phoneNumber, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void submit(String phone, String code, onLoadDateSingleListener listenter) {
        Observable<RegiterBean> observable = ServerApi.getAppAPI().getFindPasswordSecurity(phone, code,AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegiterBean>() {
            @Override
            public void onSuccess(RegiterBean bean) {
                listenter.onSuccess(bean, DataType.DATA_ONE);
            }

            @Override
            public void onFailure(String msg) {
                listenter.onFailure(msg);
            }
        });
    }
}
