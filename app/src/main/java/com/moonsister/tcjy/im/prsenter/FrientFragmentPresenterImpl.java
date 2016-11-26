package com.moonsister.tcjy.im.prsenter;


import com.hickey.network.bean.FrientBaen;
import com.hickey.tool.base.BaseIModel;
import com.moonsister.tcjy.im.model.FrientFragmentModel;
import com.moonsister.tcjy.im.model.FrientFragmentModelImpl;
import com.moonsister.tcjy.my.view.FrientFragmentView;

/**
 * Created by jb on 2016/7/8.
 */
public class FrientFragmentPresenterImpl implements FrientFragmentPresenter, BaseIModel.onLoadDateSingleListener<FrientBaen> {
    private FrientFragmentView view;
    private FrientFragmentModel model;
    private int page = 1;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(FrientFragmentView frientFragmentView) {
        this.view = frientFragmentView;
        model = new FrientFragmentModelImpl();
    }

    @Override
    public void loadBasicData(int pageType) {
        view.showLoading();
        model.loadBasicData(pageType, page, this);
        page++;
    }

    @Override
    public void loadRefresh(int pageType) {
        view.showLoading();
        model.loadBasicData(pageType, 1, this);
        page = 2;
    }

    @Override
    public void onSuccess(FrientBaen frientBaen, BaseIModel.DataType dataType) {
        view.hideLoading();
        view.setBasicData(frientBaen);

    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
