package com.moonsister.tcjy.find.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.find.model.VideoDynamicActivityModel;
import com.moonsister.tcjy.find.model.VideoDynamicActivityModelImpl;
import com.moonsister.tcjy.find.view.VideoDynamicActivityView;

import java.util.List;

/**
 * Created by jb on 2016/9/14.
 */
public class VideoDynamicActivityPresenterImpl implements VideoDynamicActivityPresenter, BaseIModel.onLoadDateSingleListener<UserInfoListBean> {
    private VideoDynamicActivityView view;
    private VideoDynamicActivityModel model;
    private int page;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(VideoDynamicActivityView view) {
        this.view = view;
        model = new VideoDynamicActivityModelImpl();
    }

    @Override
    public void loadRefresh() {
        view.showLoading();
        page = 1;
        model.loadData(page, this);
    }

    @Override
    public void loadMore() {
        view.showLoading();
        model.loadData(page, this);
    }

    @Override
    public void onSuccess(UserInfoListBean bean, BaseIModel.DataType dataType) {
        if (bean != null && bean.getData() != null) {
            List<DynamicItemBean> list = bean.getData().getList();
            if (list != null && list.size() != 0)
                page++;
            if (list != null)
                view.setVideoDynamic(list);
        }
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
