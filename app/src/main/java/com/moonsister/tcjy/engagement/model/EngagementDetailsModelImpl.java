package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.AppointmentServerApi;
import com.moonsister.tcjy.bean.EngagementDetailsBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/11/11.
 */
public class EngagementDetailsModelImpl implements EngagementDetailsModel {
    @Override
    public void loadDate(String id, onLoadDateSingleListener<EngagementDetailsBean> listener) {
        Observable<EngagementDetailsBean> observable = AppointmentServerApi.getAppAPI().getEngagemengDetails(id, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<EngagementDetailsBean>() {
            @Override
            public void onSuccess(EngagementDetailsBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }


    @Override
    public void loadByOrderIdData(String id, onLoadDateSingleListener<EngagementDetailsBean> listener) {
        Observable<EngagementDetailsBean> observable = AppointmentServerApi.getAppAPI().getEngagemengDetails2("", id, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<EngagementDetailsBean>() {
            @Override
            public void onSuccess(EngagementDetailsBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void loadByIdData2(String id, onLoadDateSingleListener<EngagementDetailsBean> listener) {
        Observable<EngagementDetailsBean> observable = AppointmentServerApi.getAppAPI().getEngagemengDetails2(id, "", UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<EngagementDetailsBean>() {
            @Override
            public void onSuccess(EngagementDetailsBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
