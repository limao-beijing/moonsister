package com.moonsister.tcjy.my.model;




import rx.Observable;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;

import com.moonsister.tcjy.bean.PersonalMessageBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

/**
 * Created by x on 2016/9/2.
 */
public class PersonalActivityModelImpl implements PersonalActivityModel {


    @Override
    public void loadData(int uid, onLoadDateSingleListener<PersonalMessageBean> listener) {
        Observable<PersonalMessageBean> observable = ServerApi.getAppAPI().setPersonalMessage(uid, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID,AppConstant.API_VERSION);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<PersonalMessageBean>() {
            @Override
            public void onSuccess(PersonalMessageBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
