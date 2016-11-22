package com.moonsister.tcjy.my.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.DefaultDataBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/7/3.
 */
public class AddCardActivityModelImpl implements AddCardActivityModel {
    @Override
    public void submitAccount(String cardNumber, String username, String cardType, String bankname, onLoadDateSingleListener listener) {
        Observable<DefaultDataBean> observable = ServerApi.getAppAPI().getsubmitAccount(cardNumber, username, cardType, bankname, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean defaultDataBean) {
                listener.onSuccess(defaultDataBean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
