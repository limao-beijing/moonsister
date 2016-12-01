package com.moonsister.tcjy.home.presenetr;

import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.base.BaseIModel;
import com.moonsister.tcjy.home.model.HomePersonFragmentModel;
import com.moonsister.tcjy.home.model.HomePersonFragmentModelImpl;
import com.moonsister.tcjy.home.view.HomePersonFragmentView;

import java.util.List;

/**
 * Created by jb on 2016/11/29.
 */
public class HomePersonFragmentPresenterImpl implements HomePersonFragmentPresenter, BaseIModel.onLoadDateSingleListener<List<EngagemengRecommendBean.DataBean>> {
    private HomePersonFragmentView view;
    private HomePersonFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(HomePersonFragmentView view) {
        this.view = view;
        model = new HomePersonFragmentModelImpl();
    }

    @Override
    public void loadInitData(int page, String type) {
        view.showLoading();
        model.loadInitData(page, type, this);
    }

    @Override
    public void onSuccess(List<EngagemengRecommendBean.DataBean> been, BaseIModel.DataType dataType) {
        view.setInitData(been);
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
