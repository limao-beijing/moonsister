package com.moonsister.tcjy.login.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.bean.RegThridBean;
import com.moonsister.tcjy.login.model.RegActivityModel;
import com.moonsister.tcjy.login.model.RegActivityModelImpl;
import com.moonsister.tcjy.login.view.RegThridActivityView;

/**
 * Created by x on 2016/8/31.
 */
public class RegActivityPresenterImpl implements RegActivityPresenter,BaseIModel.onLoadDateSingleListener<RegThridBean>{
    private RegThridActivityView view;
    private RegActivityModel model;

    @Override
    public void getThrid(String mobile, String pwd, String birthday, String code) {
        view.showLoading();
        model.getThridReg(mobile,pwd,birthday,code,this);
    }

    @Override
    public void getSecurityCode(String mobile) {
        view.showLoading();
        model.getThridReg(mobile,this);
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(RegThridActivityView regThridActivityView) {
        this.view = regThridActivityView;
        model = new RegActivityModelImpl();
    }

    @Override
    public void onSuccess(RegThridBean regThridBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (regThridBean != null) {
            if ("1".equals(regThridBean.getCode()))
                view.getThrid();
            view.requestFailed(regThridBean.getMsg());
        }
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }


}
