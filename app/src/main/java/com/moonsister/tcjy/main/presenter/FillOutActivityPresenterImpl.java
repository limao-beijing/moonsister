package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.RegFourBean;
import com.moonsister.tcjy.main.model.FillOutActivityModel;
import com.moonsister.tcjy.main.model.FillOutActivityModelImpl;
import com.moonsister.tcjy.main.view.FilloutActivityView;
import com.moonsister.tool.lang.StringUtis;

/**
 * Created by x on 2016/9/1.
 */
public class FillOutActivityPresenterImpl implements FillOutActivityPresenter,BaseIModel.onLoadDateSingleListener<RegFourBean> {
    private FilloutActivityView view;
    private FillOutActivityModel model;
    @Override
    public void fillout(String face, String nickname) {
        view.showLoading();
        model.fillout(face,nickname,this);
    }

    @Override
    public void submit(String address1) {
        view.showLoading();
        model.submit(address1,this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(FilloutActivityView filloutActivityView) {
        this.view = filloutActivityView;
        model = new FillOutActivityModelImpl();
    }

    @Override
    public void onSuccess(RegFourBean regFourBean, BaseIModel.DataType dataType) {
        if (regFourBean==null){
            view.hideLoading();
            return;
        }
        switch (dataType){
            case DATA_ZERO:
                if (StringUtis.equals(regFourBean.getCode(), AppConstant.code_request_success)){
                    view.filloutactivity();
                }
                view.transfePageMsg(regFourBean.getMsg());
                break;
        }
        view.hideLoading();
//        if (regFourBean == null) {
//            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
//        } else {
//            if (StringUtis.equals(regFourBean.getCode(), AppConstant.code_request_success)){
//                view.filloutactivity();
//            }
//            view.transfePageMsg(regFourBean.getMsg());
//        }
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
