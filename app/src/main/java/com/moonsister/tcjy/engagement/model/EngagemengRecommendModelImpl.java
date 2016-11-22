package com.moonsister.tcjy.engagement.model;


import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.AppointmentServerApi;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/27.
 */
public class EngagemengRecommendModelImpl implements EngagemengRecommendModel {
    @Override
    public void loadData(String userType, int page, EnumConstant.EngegamentType type, onLoadDateSingleListener<EngagemengRecommendBean> listener) {
        Observable<EngagemengRecommendBean> observable = AppointmentServerApi.getAppAPI().getEngagemengRecommen(userType,page, type.getType(), UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<EngagemengRecommendBean>() {
            @Override
            public void onSuccess(EngagemengRecommendBean bean) {
                listener.onSuccess(bean,DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
