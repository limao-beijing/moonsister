package com.moonsister.tcjy.home.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.KeyMateBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/8/28.
 */
public class SearchFragmentModelImpl implements SearchFragmentModel {
    @Override
    public void loadKeyMate(String key, onLoadDateSingleListener<KeyMateBean> searchFragmentPersenter) {
        Observable<KeyMateBean> observable = ServerApi.getAppAPI().getKeyMath(key, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<KeyMateBean>() {
            @Override
            public void onSuccess(KeyMateBean bean) {
                searchFragmentPersenter.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                searchFragmentPersenter.onFailure(msg);
            }
        });
    }
}
