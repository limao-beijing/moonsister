package com.hyphenate.easeui.mvp.presenter;


import com.hickey.network.bean.resposen.ChargeResBean;
import com.hickey.tool.base.BaseIModel;
import com.hyphenate.easeui.mvp.model.EaseChatRowChargeImageModel;
import com.hyphenate.easeui.mvp.model.EaseChatRowChargeImageModelImpl;
import com.hyphenate.easeui.mvp.view.EaseChatRowChargeImageView;

/**
 * Created by jb on 2016/11/25.
 */

public class EaseChatRowChargeImagePresenterImpl implements EaseChatRowChargeImagePresenter, BaseIModel.onLoadDateSingleListener<ChargeResBean> {
    private EaseChatRowChargeImageView view;
    private EaseChatRowChargeImageModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(EaseChatRowChargeImageView view) {
        this.view = view;
        model = new EaseChatRowChargeImageModelImpl();
    }

    @Override
    public void getImagePic(String lid, String acthcode) {
        view.showLoading();
        model.getImagePic(lid, acthcode, this);
    }

    @Override
    public void onSuccess(ChargeResBean bean, BaseIModel.DataType dataType) {
        view.setPic(bean);
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }

}
