package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.EngagementManagerBean;
import com.moonsister.tcjy.engagement.model.EngagementManagerFragmentModel;
import com.moonsister.tcjy.engagement.model.EngagementManagerFragmentModelImpl;
import com.moonsister.tcjy.engagement.view.EngagementManagerFragmentView;
import com.hickey.tool.constant.EnumConstant;

/**
 * Created by jb on 2016/9/28.
 */
public class EngagementManagerFragmentPresenterImpl implements EngagementManagerFragmentPresenter, BaseIModel.onLoadDateSingleListener {
    private EngagementManagerFragmentView view;
    private EngagementManagerFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(EngagementManagerFragmentView view) {
        this.view = view;
        model = new EngagementManagerFragmentModelImpl();

    }

    @Override
    public void loadData(EnumConstant.ManagerType type, int page) {
        view.showLoading();
        model.loadData(type, page, this);
    }



    @Override
    public void onSuccess(Object obj, BaseIModel.DataType dataType) {
        switch (dataType) {
            case DATA_ZERO:
                if (obj instanceof EngagementManagerBean) {
                    view.setData((EngagementManagerBean) obj);
                }
                break;
        }

        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
