package com.moonsister.tcjy.main.presenter;

import com.hickey.network.bean.resposen.IndividualResumeBean;
import com.hickey.tool.base.BaseIModel;
import com.moonsister.tcjy.main.model.IndividualResumeModel;
import com.moonsister.tcjy.main.model.IndividualResumeModelImpl;
import com.moonsister.tcjy.main.view.IndividualResumeView;

/**
 * Created by jb on 2016/12/1.
 */
public class IndividualResumePresenterImpl implements IndividualResumePresenter, BaseIModel.onLoadDateSingleListener<IndividualResumeBean> {
    private IndividualResumeView view;
    private IndividualResumeModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(IndividualResumeView view) {
        this.view = view;
        model = new IndividualResumeModelImpl();
    }

    @Override
    public void loadInitData(String uid) {
        view.showLoading();
        model.loadInitData(uid, this);
    }

    @Override
    public void onSuccess(IndividualResumeBean bean, BaseIModel.DataType dataType) {
        view.setIniData(bean);
        view.hideLoading();

    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
