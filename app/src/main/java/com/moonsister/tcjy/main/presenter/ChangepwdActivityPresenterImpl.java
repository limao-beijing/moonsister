package com.moonsister.tcjy.main.presenter;

import com.hickey.network.bean.DefaultDataBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.main.model.ChangepwdActivityModel;
import com.moonsister.tcjy.main.model.ChangepwdActivityModelImpl;
import com.moonsister.tcjy.main.view.ChangepwdActivityView;

/**
 * Created by jb on 2016/7/11.
 */
public class ChangepwdActivityPresenterImpl implements ChangepwdActivityPresenter, BaseIModel.onLoadDateSingleListener<DefaultDataBean> {
    private ChangepwdActivityView view;
    private ChangepwdActivityModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(ChangepwdActivityView changepwdActivityView) {
        this.view = changepwdActivityView;
        model = new ChangepwdActivityModelImpl();
    }

    @Override
    public void submit(String oldpwd, String newpwd) {
        view.showLoading();
        model.loadBasic(oldpwd, newpwd, this);
    }

    @Override
    public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (bean == null) {
            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
        } else {
            if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)){
                view.pageFinish();
            }
            view.transfePageMsg(bean.getMsg());
        }

    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);

    }
}
