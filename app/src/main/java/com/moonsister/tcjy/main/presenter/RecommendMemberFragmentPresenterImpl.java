package com.moonsister.tcjy.main.presenter;

import com.hickey.network.bean.RecommendMemberFragmentBean;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.main.model.RecommendMemberFragmentModel;
import com.moonsister.tcjy.main.model.RecommendMemberFragmentModelImpl;
import com.moonsister.tcjy.main.view.RecommendMemberFragmentView;

/**
 * Created by jb on 2016/8/15.
 */
public class RecommendMemberFragmentPresenterImpl implements RecommendMemberFragmentPresenter, BaseIModel.onLoadDateSingleListener<RecommendMemberFragmentBean> {
    private RecommendMemberFragmentView view;
    private RecommendMemberFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(RecommendMemberFragmentView recommendMemberFragmentView) {
        this.view = recommendMemberFragmentView;
        model = new RecommendMemberFragmentModelImpl();
    }

    @Override
    public void loadData() {
        view.showLoading();
        model.recommend(this);
    }

    @Override
    public void onSuccess(RecommendMemberFragmentBean bean, BaseIModel.DataType dataType) {
        if (bean == null) {

        } else {
            view.setData(bean);
        }
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
