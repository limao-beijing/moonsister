package com.moonsister.tcjy.login.presenter;

import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.RegiterBean;
import com.hickey.tool.ConfigUtils;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.login.model.FindPasswordActivityModel;
import com.moonsister.tcjy.login.model.FindPasswordActivityModelImpl;
import com.moonsister.tcjy.main.view.FindPasswordActivityView;
import com.moonsister.tcjy.utils.LogUtils;

/**
 * Created by jb on 2016/7/11.
 */
public class FindPasswordActivityPresenterImpl implements FindPasswordActivityPresenter, BaseIModel.onLoadDateSingleListener {
    private FindPasswordActivityView view;
    private FindPasswordActivityModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(FindPasswordActivityView findPasswordActivityView) {
        this.view = findPasswordActivityView;
        model = new FindPasswordActivityModelImpl();
    }

    @Override
    public void getSecurityCode(String phoneNumber) {
        view.showLoading();
        model.getSecurityCode(phoneNumber, this);
    }

    @Override
    public void submitRegiter(String phone, String code) {
        view.showLoading();
        model.submit(phone, code, this);
    }

    @Override
    public void onSuccess(Object bean, BaseIModel.DataType dataType) {
        if (bean == null) {
            view.hideLoading();
            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
            return;
        }
        switch (dataType) {
            case DATA_ZERO:

                if (bean != null) {
                    if ("1".equals(((BaseBean) bean).getCode()))
                        view.LoopMsg();
                    view.transfePageMsg(((BaseBean) bean).getMsg());
                }
                break;
            case DATA_ONE:
                RegiterBean rb = (RegiterBean) bean;
                if ("1".equals(rb.getCode()))
                        ConfigUtils.getInstance().getMainHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                LogUtils.e(RegiterFragmentPresenerImpl.class, "RegiterCode  : " + rb.getData().toString());
                                view.navigationNext(rb.getData().getAuthcode());
                            }
                        }, 1000);



                break;
        }
        view.hideLoading();

    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();

    }
}
