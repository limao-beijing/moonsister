package com.moonsister.tcjy.find.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.RankBean;
import com.moonsister.tcjy.find.model.RankFragmentModel;
import com.moonsister.tcjy.find.model.RankFragmentModelImpl;
import com.moonsister.tcjy.find.view.RankFragmentView;

import java.util.List;

/**
 * Created by jb on 2016/8/3.
 */
public class RankFragmentPresenterImpl implements RankFragmentPresenter, BaseIModel.onLoadListDateListener<RankBean.DataBean> {
    private RankFragmentView view;
    private RankFragmentModel model;
    private int page = 1;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(RankFragmentView rankFragmentView) {
        this.view = rankFragmentView;
        model = new RankFragmentModelImpl();
    }

    @Override
    public void refresh(int type) {
        view.showLoading();
        page = 1;
        model.loadData(type, page, this);
    }

    @Override
    public void loadMore(int type) {
        view.showLoading();
        model.loadData(type, page, this);
    }

    @Override
    public void onSuccess(List<RankBean.DataBean> t, BaseIModel.DataType dataType) {
        view.setData(t);
        view.hideLoading();
        if (page > 1 && t != null && t.size() != 0)
            page++;
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
