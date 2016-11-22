package com.moonsister.tcjy.engagement.model;

import com.hickey.network.bean.DefaultDataBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.AppointmentServerApi;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/29.
 */
public class EngegamentAppealModelImpl implements EngegamentAppealModel {
    @Override
    public void submitAppeal(String id, String content, onLoadDateSingleListener<DefaultDataBean> listenter) {
        Observable<DefaultDataBean> observable = AppointmentServerApi.getAppAPI().getSubmitEngagementAppeal(id, content, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean bean) {
                listenter.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listenter.onFailure(msg);
            }
        });
    }
}
