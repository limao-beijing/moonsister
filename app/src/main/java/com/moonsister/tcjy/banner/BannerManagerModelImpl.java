package com.moonsister.tcjy.banner;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.BannerBean;
import com.hickey.tool.phoneinfo.SystemUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.hickey.tool.ConfigUtils;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/9.
 */
public class BannerManagerModelImpl implements BannerManagerModel {
    @Override
    public void loadBannerData(onLoadDateSingleListener<BannerBean> listener) {
        Observable<BannerBean> observable = ServerApi.getAppAPI().getBannerData(SystemUtils.getScreenWeith(ConfigUtils.getInstance().getActivityContext()), SystemUtils.getScreenHeight(ConfigUtils.getInstance().getActivityContext()), UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID, "1");
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BannerBean>() {
            @Override
            public void onSuccess(BannerBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
