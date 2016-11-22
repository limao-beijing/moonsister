package com.moonsister.tcjy.dialogFragment.presenter;

import com.hickey.network.bean.EngagementPermissTextBane;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.dialogFragment.model.EngagementPermissModel;
import com.moonsister.tcjy.dialogFragment.model.EngagementPermissModelImpl;
import com.moonsister.tcjy.dialogFragment.view.EngagementPermissView;

/**
 * Created by jb on 2016/11/14.
 */
public class EngagementPermissPersenterImpl implements EngagementPermissPersenter, BaseIModel.onLoadDateSingleListener<EngagementPermissTextBane> {
    private EngagementPermissView view;
    private EngagementPermissModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(EngagementPermissView view) {
        this.view = view;
        model = new EngagementPermissModelImpl();
    }

    @Override
    public void loadTextMsg() {
        view.showLoading();
        model.loadTextMsg(this);
    }

    @Override
    public void onSuccess(EngagementPermissTextBane bean, BaseIModel.DataType dataType) {
        view.setTextMsg(bean);
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
