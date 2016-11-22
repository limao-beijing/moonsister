package com.moonsister.tcjy.center.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.LableBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/8/10.
 */
public class LableFragmentModelImpl implements LableFragmentModel {

    @Override
    public void loadData(int page, onLoadDateSingleListener<LableBean> listener) {
        int type = 0;
        int catid = 0;
        int pagesize = 8;
        Observable<LableBean> observable = ServerApi.getAppAPI().getDynamicLable(page, type, catid, pagesize, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<LableBean>() {
            @Override
            public void onSuccess(LableBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });

    }
}
