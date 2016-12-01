package com.moonsister.tcjy.home.presenetr;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.base.BaseIModel;
import com.moonsister.tcjy.home.model.HomeDynamicFragmentModel;
import com.moonsister.tcjy.home.model.HomeDynamicFragmentModelImpl;
import com.moonsister.tcjy.home.view.HomeDynamicView;

import java.util.List;

/**
 * Created by jb on 2016/11/29.
 */
public class HomeDynamicFragmentPresenterImpl implements HomeDynamicFragmentPresenter, BaseIModel.onLoadDateSingleListener<List<DynamicItemBean>> {
    private HomeDynamicView view;
    private HomeDynamicFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(HomeDynamicView view) {
        this.view = view;
        model = new HomeDynamicFragmentModelImpl();
    }

    @Override
    public void loadInitData(int page, String type) {
        view.showLoading();
        model.loadInitData(page, type, this);
    }

    @Override
    public void onSuccess(List<DynamicItemBean> bean, BaseIModel.DataType dataType) {
        view.setInitData(bean);
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
