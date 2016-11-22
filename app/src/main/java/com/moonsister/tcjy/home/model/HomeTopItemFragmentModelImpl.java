package com.moonsister.tcjy.home.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.HomeTopItemBean;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.UIUtils;

import rx.Observable;

/**
 * Created by jb on 2016/8/24.
 */
public class HomeTopItemFragmentModelImpl implements HomeTopItemFragmentModel {
    @Override
    public void loadData(int page, String tagId, EnumConstant.HomeTopFragmentTop homeType, onLoadDateSingleListener<HomeTopItemBean> listener) {

        if (homeType == null) {
            listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
            return;
        }
        Observable<HomeTopItemBean> observable = ServerApi.getAppAPI().getHomeTopItem(page, tagId, homeType.getType(), AppConstant.API_VERSION, AppConstant.CHANNEL_ID, UserInfoManager.getInstance().getAuthcode());
        ObservableUtils.parser(observable, new ObservableUtils.Callback<HomeTopItemBean>() {
            @Override
            public void onSuccess(HomeTopItemBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
