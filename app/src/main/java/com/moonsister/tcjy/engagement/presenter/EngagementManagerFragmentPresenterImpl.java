package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.EngagementManagerBean;
import com.moonsister.tcjy.bean.StatusBean;
import com.moonsister.tcjy.engagement.model.EngagementManagerFragmentModel;
import com.moonsister.tcjy.engagement.model.EngagementManagerFragmentModelImpl;
import com.moonsister.tcjy.engagement.view.EngagementManagerFragmentView;
import com.moonsister.tcjy.engagement.widget.EngagementManagerFragment;

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
    public void loadData(EngagementManagerFragment.ManagerType type, int page) {
        view.showLoading();
        model.loadData(type, page, this);
    }

    @Override
    public void submitSuccess(String datingID) {
        view.showLoading();
        model.submitSuccess(datingID, this);
    }

    @Override
    public void submitInviteSuccess(String message, String type) {
        view.showLoading();
        model.submitInviteSuccess(message, type, this);
    }

    @Override
    public void onSuccess(Object obj, BaseIModel.DataType dataType) {
        switch (dataType) {
            case DATA_ZERO:
                if (obj instanceof EngagementManagerBean) {
                    view.setData((EngagementManagerBean) obj);
                }
                break;
            case DATA_ONE:
                if (obj instanceof StatusBean) {
                    view.submitSuccess(((StatusBean) obj).getId());
                }
                break;
            case DATA_TWO:
                if (obj instanceof StatusBean) {
                    view.submitInviteSuccess((StatusBean) obj);
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
