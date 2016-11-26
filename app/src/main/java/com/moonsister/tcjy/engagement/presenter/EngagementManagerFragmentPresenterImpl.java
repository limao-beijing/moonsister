package com.moonsister.tcjy.engagement.presenter;


import com.hickey.network.bean.EngagementManagerBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.engagement.model.EngagementManagerFragmentModel;
import com.moonsister.tcjy.engagement.model.EngagementManagerFragmentModelImpl;
import com.moonsister.tcjy.engagement.view.EngagementManagerFragmentView;

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
