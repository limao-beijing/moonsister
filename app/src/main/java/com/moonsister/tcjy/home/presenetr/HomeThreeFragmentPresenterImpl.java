package com.moonsister.tcjy.home.presenetr;


import com.hickey.network.bean.BannerBean;
import com.hickey.network.bean.HomeParams;
import com.hickey.network.bean.HomeThreeFragmentBean;
import com.hickey.tool.base.BaseIModel;
import com.moonsister.tcjy.home.model.HomeThreeFragmentModel;
import com.moonsister.tcjy.home.model.HomeThreeFragmentModelImpl;
import com.moonsister.tcjy.home.view.HomeThreeFragmentView;


/**
 * Created by jb on 2016/9/25.
 */
public class HomeThreeFragmentPresenterImpl implements HomeThreeFragmentPresenter, BaseIModel.onLoadDateSingleListener {
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
    public void laodRefresh(int page, String type, int flag, HomeParams params) {
        view.showLoading();
        model.loadDate(type, params, page, flag, this);
    }

    @Override
    public void loadMore(int page, String type, int flag, HomeParams params) {
        view.showLoading();
        model.loadDate(type, params, page, flag, this);
    }

    @Override
    public void loadBannerData() {
        model.loadBannerData(this);
    }

    @Override
    public void onSuccess(Object obj, BaseIModel.DataType dataType) {
        switch (dataType) {
            case DATA_ZERO:
                if (obj instanceof HomeThreeFragmentBean) {
                    HomeThreeFragmentBean threeFragmentBean = (HomeThreeFragmentBean) obj;
                    view.setData(threeFragmentBean.getData());
                }

                break;
            case DATA_ONE:
                view.setBanner((BannerBean) obj);
                break;
        }
        view.hideLoading();


    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
