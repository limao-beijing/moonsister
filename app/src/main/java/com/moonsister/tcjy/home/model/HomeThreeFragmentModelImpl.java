package com.moonsister.tcjy.home.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.BannerBean;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.HomeParams;
import com.moonsister.tcjy.bean.HomeThreeFragmentBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.JsonUtils;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/25.
 */
public class HomeThreeFragmentModelImpl implements HomeThreeFragmentModel {
    @Override
    public void loadDate(String type, HomeParams params, int page,int flag, onLoadDateSingleListener<BaseBean> listener) {
        Observable<HomeThreeFragmentBean> observable = ServerApi.getAppAPI().getHomeThree(type, params == null ? "" : JsonUtils.serialize(params), page,flag, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<HomeThreeFragmentBean>() {
            @Override
            public void onSuccess(HomeThreeFragmentBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void loadBannerData(onLoadDateSingleListener<BannerBean> listener) {
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
