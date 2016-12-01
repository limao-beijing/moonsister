package com.moonsister.tcjy.dialogFragment.presenter;

import com.hickey.network.bean.resposen.InterestBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.base.BaseResponse;
import com.moonsister.tcjy.dialogFragment.model.InterestDialogModel;
import com.moonsister.tcjy.dialogFragment.model.InterestDialogModelImpl;
import com.moonsister.tcjy.dialogFragment.view.InterestDialogView;

import java.util.List;

/**
 * Created by jb on 2016/11/30.
 */
public class InterestDialoPresenterImpl implements InterestDialogPresenter, BaseIModel.onLoadDateSingleListener<Object> {
    private InterestDialogView view;
    private InterestDialogModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(InterestDialogView view) {
        this.view = view;
        model = new InterestDialogModelImpl();
    }

    @Override
    public void loadInitData() {
        view.showLoading();
        model.loadInitData(this);
    }

    @Override
    public void submitService(String content) {
        view.showLoading();
        model.submitService(content, this);
    }

    @Override
    public void onSuccess(Object bean, BaseIModel.DataType dataType) {
        switch (dataType) {
            case DATA_ZERO:
                if (bean instanceof List)
                    view.setInitData((List<InterestBean>) bean);
                break;
            case DATA_ONE:
                if (bean instanceof BaseResponse) {
                    view.transfePageMsg(((BaseResponse) bean).getMsg());
                    view.submitSuccess();
                }
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
