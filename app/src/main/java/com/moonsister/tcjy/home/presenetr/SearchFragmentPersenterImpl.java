package com.moonsister.tcjy.home.presenetr;

import android.view.View;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.KeyMateBean;
import com.moonsister.tcjy.home.model.SearchFragmentModel;
import com.moonsister.tcjy.home.model.SearchFragmentModelImpl;
import com.moonsister.tcjy.home.view.SearchFragmentView;

/**
 * Created by jb on 2016/8/28.
 */
public class SearchFragmentPersenterImpl implements SearchFragmentPersenter, BaseIModel.onLoadDateSingleListener<KeyMateBean> {
    private SearchFragmentView view;
    private SearchFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(SearchFragmentView searchFragmentView) {
        this.view = searchFragmentView;
        model = new SearchFragmentModelImpl();
    }

    @Override
    public void loadKeyMate(String key) {
        model.loadKeyMate(key, this);
    }

    @Override
    public void onSuccess(KeyMateBean keyMateBean, BaseIModel.DataType dataType) {
        if (keyMateBean == null) {
            view.hideLoading();
            return;
        }
        switch (dataType) {
            case DATA_ZERO:
                view.setKeyMate(keyMateBean.getData());
                break;
        }
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {

    }
}
