package com.hyphenate.easeui.mvp.presenter;

import android.app.Activity;

import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;
import com.hyphenate.easeui.mvp.model.ChargeMessageDialogModel;
import com.hyphenate.easeui.mvp.model.ChargeMessageDialogModelImpl;
import com.hyphenate.easeui.mvp.view.ChargeMessageDialogView;

/**
 * Created by jb on 2016/11/26.
 */
public class ChargeMessageDialogPresenterImpl implements ChargeMessageDialogPresenter, BaseIModel.onLoadDateSingleListener<String> {
    private ChargeMessageDialogView view;
    private ChargeMessageDialogModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(ChargeMessageDialogView view) {
        this.view = view;
        model = new ChargeMessageDialogModelImpl();
    }

    @Override
    public void pay(Activity activity, EnumConstant.PayType type, String lid, String acthcode) {
        view.showLoading();
        model.pay(activity, type, lid, acthcode, this);
    }

    @Override
    public void onSuccess(String s, BaseIModel.DataType dataType) {
        view.setSuccess();
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
