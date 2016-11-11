package com.moonsister.appointment.engagement.model;

import android.renderscript.Element;

import com.moonsister.appointment.bean.EngagemengRecommendBean;

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
                listener.onSuccess(bean, Element.DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
