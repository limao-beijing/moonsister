package com.moonsister.tcjy.my.persenter;

import com.hickey.network.bean.WithdRawDepositBean;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.my.model.WithdRawDepositModel;
import com.moonsister.tcjy.my.model.WithdRawDepositModelImpl;
import com.moonsister.tcjy.my.view.WithdRawDepositView;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/7/2.
 */
public class WithdRawDepositPresenterImpl implements WithdRawDepositPresenter, BaseIModel.onLoadDateSingleListener<WithdRawDepositBean> {
    private WithdRawDepositView view;
    private WithdRawDepositModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(WithdRawDepositView withdRawDepositView) {
        this.view = withdRawDepositView;
        model = new WithdRawDepositModelImpl();
    }

    @Override
    public void loadEnableMoney() {
        view.showLoading();
        model.loadEnableMoney(this);
    }

    @Override
    public void onSuccess(WithdRawDepositBean bean, BaseIModel.DataType dataType) {
        if (bean != null) {
            if (StringUtis.equals(bean.getCode(), "1")) {
                view.setloadEnableMoney(bean);
            } else {
                view.transfePageMsg(bean.getMsg());
            }

        } else {
            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
        }

        view.hideLoading();

    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
