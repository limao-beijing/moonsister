package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.PersonalReviseMessageBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by x on 2016/9/10.
 */
public class PersonalReviseActivityModelImpl implements PersonalReviseActivityModel {

    @Override
    public void loadpersonalData(String uid, onLoadDateSingleListener<PersonalReviseMessageBean> listener) {
        Observable<PersonalReviseMessageBean> observable = ServerApi.getAppAPI().setPersonalReviseMessage(uid, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID,AppConstant.API_VERSION);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<PersonalReviseMessageBean>() {
            @Override
            public void onSuccess(PersonalReviseMessageBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void userJson(String contents, onLoadDateSingleListener<PersonalReviseMessageBean> listener) {
        Observable<PersonalReviseMessageBean> observable = ServerApi.getAppAPI().getUserJsonBean(contents,UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID,AppConstant.API_VERSION);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<PersonalReviseMessageBean>() {
            @Override
            public void onSuccess(PersonalReviseMessageBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });

    }
}
