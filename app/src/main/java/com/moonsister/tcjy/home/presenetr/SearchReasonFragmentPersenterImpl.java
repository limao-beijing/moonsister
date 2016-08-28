package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DynamicBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.home.model.SearchReasonFragmentModel;
import com.moonsister.tcjy.home.model.SearchReasonFragmentModelImpl;
import com.moonsister.tcjy.home.view.SearchReasonFragmentView;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/8/28.
 */
public class SearchReasonFragmentPersenterImpl implements SearchReasonFragmentPersenter, BaseIModel.onLoadDateSingleListener<DynamicBean> {
    private SearchReasonFragmentView view;
    private SearchReasonFragmentModel model;
    private int page = 1;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(SearchReasonFragmentView searchReasonFragmentView) {
        this.view = searchReasonFragmentView;
        model = new SearchReasonFragmentModelImpl();
    }

    @Override
    public void loadSearchReason(String key, EnumConstant.SearchType type) {
        view.showLoading();
        model.loadSearchReason(key, type, page, this);
    }

    @Override
    public void onSuccess(DynamicBean bean, BaseIModel.DataType dataType) {
        if (bean == null) {
            view.hideLoading();
            return;
        }
//
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();

    }
}
