package com.moonsister.tcjy.center.presenter;

import com.hickey.network.bean.LableBean;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.center.model.LableFragmentModel;
import com.moonsister.tcjy.center.model.LableFragmentModelImpl;
import com.moonsister.tcjy.center.view.LableFragmentView;

/**
 * Created by jb on 2016/8/10.
 */
public class LableFragmentPersenterImpl implements LableFragmentPersenter, BaseIModel.onLoadDateSingleListener<LableBean> {
    private LableFragmentView view;
    private LableFragmentModel model;
    private int page = 1;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(LableFragmentView lableFragmentView) {
        this.view = lableFragmentView;
        model = new LableFragmentModelImpl();
    }

    @Override
    public void loadData() {
        view.showLoading();
        model.loadData(page, this);

    }

    @Override
    public void onSuccess(LableBean lableBean, BaseIModel.DataType dataType) {
        view.setLable(lableBean);
        if (lableBean != null && lableBean.getData() != null && lableBean.getData().size() != 0)
            page++;
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
