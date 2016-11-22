package com.moonsister.tcjy.permission;

import com.hickey.network.ChatServerApi;
import com.hickey.network.bean.PermissionBean;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/11/6.
 */
public class PermissionModelImpl implements PermissionModel {
    @Override
    public void checkVip(String toUid, EnumConstant.PermissionType type, onLoadDateSingleListener listener) {
        Observable<PermissionBean> observable = ChatServerApi.getAppAPI().checkVip(toUid,UserInfoManager.getInstance().getAuthcode(), type.getType(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<PermissionBean>() {
            @Override
            public void onSuccess(PermissionBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
