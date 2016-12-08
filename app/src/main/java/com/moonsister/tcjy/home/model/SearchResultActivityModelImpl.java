package com.moonsister.tcjy.home.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.SearchReasonBaen;
import com.hickey.tool.base.BaseIModel;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/7/10.
 */
public class SearchResultActivityModelImpl implements SearchResultActivityModel {
    @Override
    public void loadBasicData(String key, int page, BaseIModel.onLoadDateSingleListener<SearchReasonBaen> listener) {
        Observable<SearchReasonBaen> observable = ServerApi.getAppAPI().getSearchReason(key.trim(), page, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<SearchReasonBaen>() {
            @Override
            public void onSuccess(SearchReasonBaen bean) {
                listener.onSuccess(bean, BaseIModel.DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
