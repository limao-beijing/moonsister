package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DynamicBean;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.home.model.SearchReasonFragmentModel;
import com.moonsister.tcjy.home.model.SearchReasonFragmentModelImpl;
import com.moonsister.tcjy.home.view.SearchReasonFragmentView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;

import java.util.List;

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
    public void loadSearchReason(String key, boolean isLoadMore, EnumConstant.SearchType type) {
        if (isLoadMore) {
            page++;
        } else {
            page = 1;
        }

        view.showLoading();
        model.loadSearchReason(key, type, page, this);
    }

    @Override
    public void onSuccess(DynamicBean bean, BaseIModel.DataType dataType) {
        if (bean == null) {
            view.hideLoading();
            return;
        }
        List<DynamicItemBean> data = bean.getData();
        if (page > 1 && data.size() <= 0)
            page--;
        view.setLoadResult(data);
//
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();

    }
}
