package com.moonsister.tcjy.my.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.UserDetailBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by x on 2016/9/8.
 */
public class HreatFragmentModelImpl implements HreatFragmentModel {

    @Override
    public void loadData(String uid, onLoadDateSingleListener listener) {
        Observable<UserDetailBean> observable = ServerApi.getAppAPI().userdetailbean(uid,UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<UserDetailBean>() {
            @Override
            public void onSuccess(UserDetailBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
