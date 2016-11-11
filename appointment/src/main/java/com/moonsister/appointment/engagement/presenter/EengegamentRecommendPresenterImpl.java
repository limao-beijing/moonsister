package com.moonsister.appointment.engagement.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.EngagemengRecommendBean;
import com.moonsister.tcjy.engagement.model.EngagemengRecommendModel;
import com.moonsister.tcjy.engagement.model.EngagemengRecommendModelImpl;
import com.moonsister.tcjy.engagement.view.EngagemengRecommendView;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/9/27.
 */
public class EengegamentRecommendPresenterImpl implements EengegamentRecommendPresenter, BaseIModel.onLoadDateSingleListener<EngagemengRecommendBean> {
    private EngagemengRecommendView view;
    private EngagemengRecommendModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(EngagemengRecommendView view) {
        this.view = view;
        model = new EngagemengRecommendModelImpl();

    }

    @Override
    public void loadData(int page, EnumConstant.EngegamentType type) {
        view.showLoading();
        model.loadData(page, type, this);
    }

    @Override
    public void onSuccess(EngagemengRecommendBean bean, BaseIModel.DataType dataType) {
        view.setRecommend(bean.getData());
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
