package com.moonsister.tcjy.main.model;

import com.moonsister.pay.tencent.PayBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.AuthenticationBean;
import com.moonsister.tcjy.bean.RegOneBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.UIUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by x on 2016/8/30.
 */
public class ManorGrilActivityModelImpl implements ManorGrilActivityModel {

    @Override
    public void regone(int sex, onLoadDateSingleListener<RegOneBean> listener) {
        Observable<RegOneBean> observable = ServerApi.getAppAPI().getRegOneBean(sex,AppConstant.API_VERSION, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegOneBean>() {
            @Override
            public void onSuccess(RegOneBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
