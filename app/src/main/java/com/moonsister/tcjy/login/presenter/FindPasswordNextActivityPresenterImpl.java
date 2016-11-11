package com.moonsister.tcjy.login.presenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.login.model.FindPasswordNextActivityModel;
import com.moonsister.tcjy.login.model.FindPasswordNextActivityModelImpl;
import com.moonsister.tcjy.login.view.FindPasswordNextActivityView;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tool.lang.StringUtis;

/**
 * Created by jb on 2016/7/11.
 */
public class FindPasswordNextActivityPresenterImpl implements FindPasswordNextActivityPresenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private FindPasswordNextActivityView view;
    private FindPasswordNextActivityModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(FindPasswordNextActivityView findPasswordNextActivityView) {
        this.view = findPasswordNextActivityView;
        model = new FindPasswordNextActivityModelImpl();
    }

    @Override
    public void submit(String newpwd, String code) {
        view.showLoading();
        model.submit(newpwd, code, this);
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        if (bean == null) {
            view.hideLoading();
            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
        } else {
            if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                UIUtils.sendDelayedOneMillis(new Runnable() {
                    @Override
                    public void run() {
                        view.pageFinish();
                    }
                });
            }
            view.transfePageMsg(bean.getMsg());
            view.hideLoading();
        }
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
