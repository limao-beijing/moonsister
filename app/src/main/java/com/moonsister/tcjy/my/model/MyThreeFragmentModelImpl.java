package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.MyThreeFragmentBean;
import com.moonsister.tcjy.bean.UserDetailBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/25.
 */
public class MyThreeFragmentModelImpl implements MyThreeFragmentModel {
    @Override
    public void loadData(int page, String type, onLoadDateSingleListener<BaseBean> listener) {
        Observable<MyThreeFragmentBean> observable = ServerApi.getAppAPI().getMyThreeFragment(page, type, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<MyThreeFragmentBean>() {
            @Override
            public void onSuccess(MyThreeFragmentBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void loadHeaderData(onLoadDateSingleListener<BaseBean> listener) {
        Observable<UserDetailBean> observable = ServerApi.getAppAPI().userdetailbean(UserInfoManager.getInstance().getUid(), UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<UserDetailBean>() {
            @Override
            public void onSuccess(UserDetailBean bean) {
                listener.onSuccess(bean, DataType.DATA_ONE);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
