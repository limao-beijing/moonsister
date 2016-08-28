package com.moonsister.tcjy.home.presenetr;

import android.view.View;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.ChooseKeyBean;
import com.moonsister.tcjy.home.model.SearchContentFragmentModel;
import com.moonsister.tcjy.home.model.SearchContentFragmentModelImpl;
import com.moonsister.tcjy.home.view.SearchContentFragmentView;

/**
 * Created by jb on 2016/8/28.
 */
public class SearchContentFragmentPersenterImpl implements SearchContentFragmentPersenter, BaseIModel.onLoadDateSingleListener<ChooseKeyBean> {
    private SearchContentFragmentView view;
    private SearchContentFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(SearchContentFragmentView searchContentFragmentView) {
        this.view = searchContentFragmentView;
        model = new SearchContentFragmentModelImpl();

    }

    @Override
    public void loadChooseKey() {
        model.loadChooseKey(this);

    }


    @Override
    public void onSuccess(ChooseKeyBean chooseKeyBean, BaseIModel.DataType dataType) {
        if (chooseKeyBean == null) {
            view.hideLoading();
            return;
        }
        switch (dataType) {
            case DATA_ZERO:
                view.setChooseKeys(chooseKeyBean.getData());
                break;
        }
        view.hideLoading();

    }

    @Override
    public void onFailure(String msg) {

    }
}
