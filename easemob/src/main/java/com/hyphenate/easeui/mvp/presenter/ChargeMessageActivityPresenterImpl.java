package com.hyphenate.easeui.mvp.presenter;

import android.app.Activity;

import com.hickey.network.bean.resposen.BaseModel;
import com.hickey.network.bean.resposen.ChargeInitBean;
import com.hickey.network.bean.resposen.ChargeMessageBean;
import com.hickey.tool.base.BaseIModel;
import com.hyphenate.easeui.mvp.model.ChargeMessageActivityModel;
import com.hyphenate.easeui.mvp.model.ChargeMessageActivityModelImpl;
import com.hyphenate.easeui.mvp.view.ChargeMessageActivityView;

import java.util.List;

/**
 * Created by jb on 2016/11/26.
 */
public class ChargeMessageActivityPresenterImpl implements ChargeMessageActivityPresenter, BaseIModel.onLoadDateSingleListener<BaseModel> {
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
    public void submitData(boolean checked, String money, List<String> contents, String desc, int type, String uid, long duration, String authcode) {
        view.showLoading();
        model.submitData(((Activity) view).getApplicationContext(),checked, money, contents, desc, type, uid, duration, authcode, this);
    }

    @Override
    public void loadInitData(String authcode) {
        view.showLoading();
        model.loadInitData(authcode, this);
    }

    @Override
    public void onSuccess(BaseModel model, BaseIModel.DataType dataType) {
        switch (dataType) {
            case DATA_ZERO:
                if (model instanceof ChargeMessageBean) {
                    view.hideLoading();
                    view.setData((ChargeMessageBean) model);
                }
                break;
            case DATA_ONE:
                if (model instanceof ChargeInitBean) {
                    view.setInitData((ChargeInitBean) model);
                    view.hideLoading();
                }
                break;
        }

    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
