package com.moonsister.tcjy.engagement.presenter;

import com.hickey.network.bean.EngagementDetailsBean;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.engagement.model.EngagementDetailsModel;
import com.moonsister.tcjy.engagement.model.EngagementDetailsModelImpl;
import com.moonsister.tcjy.engagement.view.EngagementDetailsView;

/**
 * Created by jb on 2016/11/11.
 */
public class EngagementDetailsPersenterImpl implements EngagementDetailsPersenter, BaseIModel.onLoadDateSingleListener<EngagementDetailsBean> {
    private EngagementDetailsView view;
    private EngagementDetailsModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(EngagementDetailsView view) {
        this.view = view;
        model = new EngagementDetailsModelImpl();
    }

    @Override
    public void loadByIdData(String id) {
        view.showLoading();
        model.loadDate(id, this);
    }

    @Override
    public void loadByIdData2(String id) {
        view.showLoading();
        model.loadByIdData2(id,this);
    }

    @Override
    public void loadByOrderIdData2(String id) {
        view.showLoading();
        model.loadByOrderIdData(id,this);
    }

    @Override
    public void onSuccess(EngagementDetailsBean bean, BaseIModel.DataType dataType) {
        if (bean != null && StringUtis.equals(bean.getCode(), "1") && bean.getData() != null)
            view.setData(bean.getData());
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
