package com.moonsister.tcjy.engagement.presenter;

import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.engagement.model.EngagementActionModel;
import com.moonsister.tcjy.engagement.model.EngagementActionModelImpl;
import com.moonsister.tcjy.engagement.view.EngagementActionView;

/**
 * Created by jb on 2016/11/12.
 */
public class EngagementActionPersenterImpl implements EngagementActionPersenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private EngagementActionView view;
    private EngagementActionModel actionModel;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(EngagementActionView view) {
        this.view = view;
        actionModel = new EngagementActionModelImpl();

    }

    @Override
    public void actionEngagement(String id, int actionType) {
        view.showLoading();
        actionModel.actionEngagement(id, actionType, this);
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        if (StringUtis.equals(bean.getCode(), "1")) {
            view.actionSuccess();
        }
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }

}
