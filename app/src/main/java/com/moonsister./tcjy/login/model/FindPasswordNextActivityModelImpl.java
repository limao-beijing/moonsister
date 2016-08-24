package com.moonsister.tcjy.login.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.utils.MD5Util;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/7/11.
 */
public class FindPasswordNextActivityModelImpl implements FindPasswordNextActivityModel {
    @Override
    public void submit(String newpwd, String code, onLoadDateSingleListener listenter) {
        Observable<BaseBean> observable = ServerApi.getAppAPI().getFindPasswordNext(MD5Util.string2MD5(newpwd), code, AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                listenter.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listenter.onFailure(msg);
            }
        });
    }
}
