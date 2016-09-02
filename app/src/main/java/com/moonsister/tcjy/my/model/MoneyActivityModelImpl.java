package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BalanceBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by x on 2016/9/2.
 */
public class MoneyActivityModelImpl implements MoneyActivityModel {
    @Override
    public void money(int type, int page, int pagesize, onLoadDateSingleListener<BalanceBean> listener) {
        Observable<BalanceBean> observable = ServerApi.getAppAPI().balance(type,page,pagesize, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BalanceBean>() {
            @Override
            public void onSuccess(BalanceBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
