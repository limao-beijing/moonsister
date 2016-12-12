package com.moonsister.tcjy.my.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.MyThreeFragmentBean;
import com.hickey.network.bean.StatusBean;
import com.hickey.network.bean.UserDetailBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/25.
 */
public class MyThreeFragmentModelImpl implements MyThreeFragmentModel {
    @Override
    public void loadData(String uid, int page, String type, onLoadDateSingleListener<BaseBean> listener) {
        Observable<MyThreeFragmentBean> observable = ServerApi.getAppAPI().getMyThreeFragment(uid, page, type, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
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
    public void loadHeaderData(String uid, onLoadDateSingleListener<BaseBean> listener) {
        Observable<UserDetailBean> observable = ServerApi.getAppAPI().userdetailbean(uid, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
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

    @Override
    public void deleteRes(String id, onLoadDateSingleListener<BaseBean> listener) {
        Observable<StatusBean> observable = ServerApi.getAppAPI().getDeleteRes(id, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<StatusBean>() {
            @Override
            public void onSuccess(StatusBean bean) {
                if (bean != null)
                    bean.setId(id);
                listener.onSuccess(bean, DataType.DATA_TWO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
