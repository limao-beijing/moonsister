package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.main.model.HomePageFragmentModel;
import com.moonsister.tcjy.main.model.HomePageFragmentModelImpl;
import com.moonsister.tcjy.main.view.HomePageFragmentView;

import java.util.List;

/**
 * Created by jb on 2016/9/1.
 */
public class HomePageFragmentPresenterImpl implements HomePageFragmentPresenter, BaseIModel.onLoadDateSingleListener {
    private HomePageFragmentView view;
    private HomePageFragmentModel model;
    private int page;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(HomePageFragmentView homePageFragmentView) {
        this.view = homePageFragmentView;
        model = new HomePageFragmentModelImpl();
    }

    @Override
    public void loadRefresh(String userId) {
        view.showLoading();
        page = 1;
        model.loadDynamicData(userId, page, this);

    }

    @Override
    public void loadHeader(String userId) {
        model.loadheaderData(userId, this);
    }

    @Override
    public void loadMore(String userId) {
        view.showLoading();
        model.loadDynamicData(userId, page, this);
    }

    @Override
    public void onSuccess(Object object, BaseIModel.DataType dataType) {
        if (object == null) {
            view.hideLoading();
            return;
        }
        switch (dataType) {
            case DATA_ZERO:
                if (object instanceof List) {
                    List<DynamicItemBean> list = (List<DynamicItemBean>) object;
                    if (list != null && list.size() > 0)
                        page++;
                    view.setDynamicData(list);
                }
                break;

            case DATA_ONE:
                if (object instanceof UserInfoDetailBean) {
                    UserInfoDetailBean bean = (UserInfoDetailBean) object;
                    view.setHeaderData(bean);
                }
                break;
        }
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {

    }
}
