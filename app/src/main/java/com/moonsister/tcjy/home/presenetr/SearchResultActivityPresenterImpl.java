package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.SearchReasonBaen;
import com.moonsister.tcjy.home.model.SearchResultActivityModel;
import com.moonsister.tcjy.home.model.SearchResultActivityModelImpl;
import com.moonsister.tcjy.home.view.SearchResultActivityView;

/**
 * Created by jb on 2016/7/10.
 */
public class SearchResultActivityPresenterImpl implements SearchResultActivityPresenter, BaseIModel.onLoadDateSingleListener<SearchReasonBaen> {
    private SearchResultActivityView view;
    private SearchResultActivityModel model;
    private int page = 1;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(SearchResultActivityView searchReasonActivityView) {
        this.view = searchReasonActivityView;
        model = new SearchResultActivityModelImpl();
    }

    @Override
    public void loadBasicData(String key) {
        view.showLoading();
        model.loadBasicData(key, page, this);
    }

    @Override
    public void onSuccess(SearchReasonBaen searchReasonBaen, BaseIModel.DataType dataType) {
        view.setReasonData(searchReasonBaen);
        view.hideLoading();

    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
