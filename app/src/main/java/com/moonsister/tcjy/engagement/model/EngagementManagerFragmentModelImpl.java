package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.EngagementManagerBean;
import com.moonsister.tcjy.bean.StatusBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/28.
 */
public class EngagementManagerFragmentModelImpl implements EngagementManagerFragmentModel {
    @Override
    public void loadData(EnumConstant.ManagerType type, int page, onLoadDateSingleListener listener) {
        Observable<EngagementManagerBean> observable = ServerApi.getAppAPI().getEngagementList(page, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        if (type == EnumConstant.ManagerType.passivity) {
            observable = ServerApi.getAppAPI().getEngagementPassivityList(page, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        } else {
            observable = ServerApi.getAppAPI().getEngagementList(page, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        }

        ObservableUtils.parser(observable, new ObservableUtils.Callback<EngagementManagerBean>() {
            @Override
            public void onSuccess(EngagementManagerBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void submitSuccess(String id, onLoadDateSingleListener listener) {
        Observable<StatusBean> observable = ServerApi.getAppAPI().getSubmitSuccess(id, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<StatusBean>() {
            @Override
            public void onSuccess(StatusBean bean) {
                bean.setId(id);
                listener.onSuccess(bean, DataType.DATA_ONE);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void submitInviteSuccess(String id,String type, onLoadDateSingleListener listener) {
        Observable<StatusBean> observable = ServerApi.getAppAPI().getsubmitInviteSuccess(id,type ,UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<StatusBean>() {
            @Override
            public void onSuccess(StatusBean bean) {
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
