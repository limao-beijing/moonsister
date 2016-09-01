package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.RegFourBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by x on 2016/9/1.
 */
public class FillOutActivityModelImpl implements FillOutActivityModel  {
    @Override
    public void fillout(String face, String nickname, onLoadDateSingleListener<RegFourBean> listener) {
        Observable<RegFourBean> observable = ServerApi.getAppAPI().getRegFourBean(face,nickname, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegFourBean>() {
            @Override
            public void onSuccess(RegFourBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
