package com.moonsister.tcjy.find.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.NearbyBean;
import com.moonsister.tcjy.find.model.NearbyActivityModel;
import com.moonsister.tcjy.find.model.NearbyActivityModelImpl;
import com.moonsister.tcjy.find.view.NearbyActivityView;

import java.util.List;

/**
 * Created by jb on 2016/8/4.
 */
public class NearbyActivityPresenterImpl implements NearbyActivityPresenter, BaseIModel.onLoadListDateListener<NearbyBean.DataBean> {
    private NearbyActivityView view;
    private NearbyActivityModel model;
    private int page = 1;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(NearbyActivityView nearbyActivityView) {
        this.view = nearbyActivityView;
        model = new NearbyActivityModelImpl();
    }

    @Override
    public void refresh(String sex) {
        view.showLoading();
        page = 1;
        model.loadData(sex, page, this);

    }

    @Override
    public void loadMore(String sex) {
        view.showLoading();
        model.loadData(sex, page, this);
    }

    @Override
    public void onSuccess(List<NearbyBean.DataBean> t, BaseIModel.DataType dataType) {
        view.setData(t);
        if (t != null) {
            if (t.size() != 0)
                page++;
        }

        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
