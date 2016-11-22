package com.moonsister.tcjy.home.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.ChooseKeyBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/8/28.
 */
public class SearchContentFragmentModelImpl implements SearchContentFragmentModel {
    @Override
    public void loadChooseKey(onLoadDateSingleListener<ChooseKeyBean> listener) {
        Observable<ChooseKeyBean> observable = ServerApi.getAppAPI().getLoadChooesKey(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<ChooseKeyBean>() {
            @Override
            public void onSuccess(ChooseKeyBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
