package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.TiXinrRecordBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/7/2.
 */
public class TiXianFragmentModelImpl implements TiXianFragmentModel {
    @Override
    public void loadTixin(onLoadDateSingleListener listener) {
        Observable<TiXinrRecordBean> observable = ServerApi.getAppAPI().getTixinRecord(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<TiXinrRecordBean>() {
            @Override
            public void onSuccess(TiXinrRecordBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });


    }
}
