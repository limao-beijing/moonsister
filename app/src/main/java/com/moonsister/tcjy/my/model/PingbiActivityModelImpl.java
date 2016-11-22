package com.moonsister.tcjy.my.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.PingbiBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by x on 2016/9/14.
 */
public class PingbiActivityModelImpl implements PingbiActivityModel {

    @Override
    public void loadData(String page, onLoadDateSingleListener<PingbiBean> listener) {
        Observable<PingbiBean> observable = ServerApi.getAppAPI().pingbiliebiao(page,UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID,AppConstant.API_VERSION);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<PingbiBean>() {
            @Override
            public void onSuccess(PingbiBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
