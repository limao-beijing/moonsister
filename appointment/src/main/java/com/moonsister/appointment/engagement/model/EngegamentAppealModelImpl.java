package com.moonsister.appointment.engagement.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.StatusBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/29.
 */
public class EngegamentAppealModelImpl implements EngegamentAppealModel {
    @Override
    public void submitAppeal(String id, String content, onLoadDateSingleListener<StatusBean> listenter) {
        Observable<StatusBean> observable = ServerApi.getAppAPI().getSubmitEngagementAppeal(id, content, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<StatusBean>() {
            @Override
            public void onSuccess(StatusBean bean) {
                listenter.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listenter.onFailure(msg);
            }
        });
    }
}
