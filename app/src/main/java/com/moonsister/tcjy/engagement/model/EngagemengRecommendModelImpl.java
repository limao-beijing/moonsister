package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.EngagemengRecommendBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/27.
 */
public class EngagemengRecommendModelImpl implements EngagemengRecommendModel {
    @Override
    public void loadData(int page, EnumConstant.EngegamentType type, onLoadDateSingleListener<EngagemengRecommendBean> listener) {
        Observable<EngagemengRecommendBean> observable = ServerApi.getAppAPI().getEngagemengRecommen(page, type.getType(), UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<EngagemengRecommendBean>() {
            @Override
            public void onSuccess(EngagemengRecommendBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
