package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.WithdRawDepositBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/7/2.
 */
public class WithdRawDepositModelImpl implements WithdRawDepositModel {
    @Override
    public void loadEnableMoney(onLoadDateSingleListener<WithdRawDepositBean> listener) {
        Observable<WithdRawDepositBean> observable = ServerApi.getAppAPI().getEnableMoney(UserInfoManager.getInstance().getAuthcode(), AppConstant.API_VERSION, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<WithdRawDepositBean>() {
            @Override
            public void onSuccess(WithdRawDepositBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
