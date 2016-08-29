package com.moonsister.tcjy.home.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.DynamicBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.EnumConstant;
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
