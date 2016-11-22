package com.moonsister.tcjy.home.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.DynamicBean;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/8/28.
 */
public class SearchReasonFragmentModelImpl implements SearchReasonFragmentModel {
    @Override
    public void loadSearchReason(String key, EnumConstant.SearchType type, int page, onLoadDateSingleListener<DynamicBean> listener) {
        Observable<DynamicBean> observable = ServerApi.getAppAPI().getSearchFragmentReason(key, type.getType(), page, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID, AppConstant.API_VERSION);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<DynamicBean>() {
            @Override
            public void onSuccess(DynamicBean baen) {
                listener.onSuccess(baen, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
