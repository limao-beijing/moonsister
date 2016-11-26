package com.moonsister.tcjy.login.presenter;

import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.login.model.RegActivityModel;
import com.moonsister.tcjy.login.model.RegActivityModelImpl;
import com.moonsister.tcjy.login.view.RegThridActivityView;

/**
 * Created by x on 2016/8/31.
 */
public class RegActivityPresenterImpl implements RegActivityPresenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private RegThridActivityView view;
    private RegActivityModel model;

    @Override
    public void getThrid(String mobile, String pwd, String birthday, String code) {
        view.showLoading();
        model.getThridReg(mobile, pwd, birthday, code, this);
    }

    @Override
    public void getSecurityCode(String mobile) {
        view.showLoading();
        model.getSecurityCode(mobile, this);
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
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        if (bean == null) {
            view.hideLoading();
            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
            return;
        }
        switch (dataType) {
            case DATA_ZERO:
                if (StringUtis.equals(bean.getCode(), "1")) {
                    view.LoopMsg();
                }

                break;
            case DATA_ONE:
                if (StringUtis.equals("1", bean.getCode())) {
                    view.finishPage();
                }
                break;
        }
        view.transfePageMsg(bean.getMsg());
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }


}
