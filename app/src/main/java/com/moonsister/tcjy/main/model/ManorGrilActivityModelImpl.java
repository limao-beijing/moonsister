package com.moonsister.tcjy.main.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.RegOneBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by x on 2016/8/30.
 */
public class ManorGrilActivityModelImpl implements ManorGrilActivityModel {

    @Override
    public void regone(int sex, onLoadDateSingleListener<RegOneBean> listener) {
        Observable<RegOneBean> observable = ServerApi.getAppAPI().getRegOneBean(sex,AppConstant.API_VERSION, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegOneBean>() {
            @Override
            public void onSuccess(RegOneBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
