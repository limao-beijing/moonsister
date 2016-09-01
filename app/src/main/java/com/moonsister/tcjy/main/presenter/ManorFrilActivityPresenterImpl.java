package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.bean.RegOneBean;
import com.moonsister.tcjy.main.model.ManorGrilActivityModel;
import com.moonsister.tcjy.main.model.ManorGrilActivityModelImpl;
import com.moonsister.tcjy.main.view.ManorGrilActivityView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/8/31.
 */
public class ManorFrilActivityPresenterImpl implements ManorFrilActivityPresenter,BaseIModel.onLoadDateSingleListener<RegOneBean> {
    private ManorGrilActivityView view;
    private ManorGrilActivityModel model;

    @Override
    public void regOne(int sex) {
        view.showLoading();
        model.regone(sex,this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(ManorGrilActivityView manorGrilActivityView) {
        this.view = manorGrilActivityView;
        model = new ManorGrilActivityModelImpl();
    }

    @Override
    public void onSuccess(RegOneBean regOneBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (regOneBean == null) {
            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
        } else {
            if (StringUtis.equals(regOneBean.getCode(), AppConstant.code_request_success)){
                view.getReg(regOneBean.getData().getAuthcode());
            }
            view.transfePageMsg(regOneBean.getMsg());
        }
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
