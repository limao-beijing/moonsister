package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.AppointmentServerApi;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/11/12.
 */
public class EngagementActionModelImpl implements EngagementActionModel {


    @Override
    public void actionEngagement(String id, int type, onLoadDateSingleListener<BaseBean> listener) {
        Observable<BaseBean> observable = AppointmentServerApi.getAppAPI().getActionEngagement(id, type, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                listener.onSuccess(bean, DataType.DATA_ONE);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
