package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.EngagementDetailsBean;
import com.moonsister.tcjy.engagement.model.EngagementDetailsModel;
import com.moonsister.tcjy.engagement.model.EngagementDetailsModelImpl;
import com.moonsister.tcjy.engagement.view.EngagementDetailsView;
import com.moonsister.tool.lang.StringUtis;

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
    public void loadData(String id) {
        view.showLoading();
        model.loadDate(id, this);
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
