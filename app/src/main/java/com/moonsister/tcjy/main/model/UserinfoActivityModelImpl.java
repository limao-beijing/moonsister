package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.UserInfoChangeBean;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/7/11.
 */
public class UserinfoActivityModelImpl implements UserinfoActivityModel {
    @Override
    public void loadBasicData(String uid, onLoadDateSingleListener<UserInfoChangeBean> listener) {
        Observable<UserInfoChangeBean> observable = ServerApi.getAppAPI().getUserInfoBasic(uid, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<UserInfoChangeBean>() {
            @Override
            public void onSuccess(UserInfoChangeBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
