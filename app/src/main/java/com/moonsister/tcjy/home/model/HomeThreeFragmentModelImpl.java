package com.moonsister.tcjy.home.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.BannerBean;
import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.HomeParams;
import com.hickey.network.bean.HomeThreeFragmentBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.parse.JsonUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/25.
 */
public class HomeThreeFragmentModelImpl implements HomeThreeFragmentModel {
    @Override
    public void loadDate(String type, HomeParams params, int page, int flag, BaseIModel.onLoadDateSingleListener<BaseBean> listener) {
        Observable<HomeThreeFragmentBean> observable = ServerApi.getAppAPI().getHomeThree(type, params == null ? "" : JsonUtils.serialize(params), page,flag, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<HomeThreeFragmentBean>() {
            @Override
            public void onSuccess(HomeThreeFragmentBean bean) {
                listener.onSuccess(bean, BaseIModel.DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void loadBannerData(BaseIModel.onLoadDateSingleListener<BannerBean> listener) {
        Observable<BannerBean> observable = ServerApi.getAppAPI().getloadBannerData(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BannerBean>() {
            @Override
            public void onSuccess(BannerBean bean) {
                listener.onSuccess(bean, DataType.DATA_ONE);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
