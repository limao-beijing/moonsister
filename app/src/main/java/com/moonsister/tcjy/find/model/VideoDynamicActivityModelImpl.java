package com.moonsister.tcjy.find.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.UserInfoListBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/14.
 */
public class VideoDynamicActivityModelImpl implements VideoDynamicActivityModel {

    @Override
    public void loadData(int page, onLoadDateSingleListener<UserInfoListBean> listener) {
        Observable<UserInfoListBean> observable = ServerApi.getAppAPI().getVideoList(page, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<UserInfoListBean>() {
            @Override
            public void onSuccess(UserInfoListBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
