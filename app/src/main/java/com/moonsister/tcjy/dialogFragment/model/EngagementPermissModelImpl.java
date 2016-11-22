package com.moonsister.tcjy.dialogFragment.model;

import com.hickey.network.bean.EngagementPermissTextBane;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.AppointmentServerApi;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/11/14.
 */
public class EngagementPermissModelImpl implements EngagementPermissModel {
    @Override
    public void loadTextMsg(onLoadDateSingleListener<EngagementPermissTextBane> listener) {
        Observable<EngagementPermissTextBane> observable = AppointmentServerApi.getAppAPI().getTextMsg(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<EngagementPermissTextBane>() {
            @Override
            public void onSuccess(EngagementPermissTextBane bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
