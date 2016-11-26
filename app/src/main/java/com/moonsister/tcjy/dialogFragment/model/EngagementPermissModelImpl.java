package com.moonsister.tcjy.dialogFragment.model;

import com.hickey.network.ModuleServerApi;
import com.hickey.network.bean.EngagementPermissTextBane;
import com.hickey.tool.base.BaseIModel;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/11/14.
 */
public class EngagementPermissModelImpl implements EngagementPermissModel {
    @Override
    public void loadTextMsg(BaseIModel.onLoadDateSingleListener<EngagementPermissTextBane> listener) {
        Observable<EngagementPermissTextBane> observable = ModuleServerApi.getAppAPI().getTextMsg(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<EngagementPermissTextBane>() {
            @Override
            public void onSuccess(EngagementPermissTextBane bean) {
                listener.onSuccess(bean, BaseIModel.DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
