package com.hyphenate.easeui.mvp.presenter;

import android.app.Activity;

import com.hickey.network.bean.resposen.ChargeMessageBean;
import com.hickey.tool.base.BaseIModel;
import com.hyphenate.easeui.mvp.model.ChargeMessageActivityModel;
import com.hyphenate.easeui.mvp.model.ChargeMessageActivityModelImpl;
import com.hyphenate.easeui.mvp.view.ChargeMessageActivityView;

import java.util.List;

/**
 * Created by jb on 2016/11/26.
 */
public class ChargeMessageActivityPresenterImpl implements ChargeMessageActivityPresenter, BaseIModel.onLoadDateSingleListener<ChargeMessageBean> {
    private ChargeMessageActivityView view;
    private ChargeMessageActivityModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(ChargeMessageActivityView view) {
        this.view = view;
        model = new ChargeMessageActivityModelImpl();
    }

    @Override
    public void submitData(String money, List<String> contents, String desc, int type, String uid, long duration, String authcode) {
        view.showLoading();
        model.submitData(((Activity) view).getApplicationContext(), money, contents, desc, type, uid, duration, authcode, this);
    }

    @Override
    public void onSuccess(ChargeMessageBean bean, BaseIModel.DataType dataType) {
        view.hideLoading();
        view.setData(bean);
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
