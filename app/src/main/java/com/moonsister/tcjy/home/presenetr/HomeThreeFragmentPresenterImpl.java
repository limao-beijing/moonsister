package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.HomeParams;
import com.moonsister.tcjy.bean.HomeThreeFragmentBean;
import com.moonsister.tcjy.home.model.HomeThreeFragmentModel;
import com.moonsister.tcjy.home.model.HomeThreeFragmentModelImpl;
import com.moonsister.tcjy.home.view.HomeThreeFragmentView;

/**
 * Created by jb on 2016/9/25.
 */
public class HomeThreeFragmentPresenterImpl implements HomeThreeFragmentPresenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private HomeThreeFragmentView view;
    private HomeThreeFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(HomeThreeFragmentView view) {
        this.view = view;
        model = new HomeThreeFragmentModelImpl();
    }

    @Override
    public void laodRefresh(int page, String type, HomeParams params) {
        view.showLoading();
        model.loadDate(type, params, page, this);
    }

    @Override
    public void loadMore(int page, String type, HomeParams params) {
        view.showLoading();
        model.loadDate(type, params, page, this);
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        switch (dataType) {
            case DATA_ZERO:
                if (bean instanceof HomeThreeFragmentBean) {
                    HomeThreeFragmentBean threeFragmentBean = (HomeThreeFragmentBean) bean;
                    view.setData(threeFragmentBean.getData());
                }

                break;
        }
        view.hideLoading();


    }

    @Override
    public void onFailure(String msg) {

    }
}
