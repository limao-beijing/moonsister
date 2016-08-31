package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.main.model.BuyVipFragmentModel;
import com.moonsister.tcjy.main.model.BuyVipFragmentModelImpl;
import com.moonsister.tcjy.main.view.BuyVipFragmentView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.Objects;

/**
 * Created by jb on 2016/8/13.
 */
public class BuyVipFragmentPersenterImpl implements BuyVipFragmentPersenter, BaseIModel.onLoadDateSingleListener<String> {
    private BuyVipFragmentView view;
    private BuyVipFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(BuyVipFragmentView buyVipFragmentView) {
        this.view = buyVipFragmentView;
        model = new BuyVipFragmentModelImpl();
    }

    @Override
    public void buyVIP(int number) {
        view.showLoading();
        model.buyVIP(EnumConstant.PayType.IAPP_PAY, number, this);
    }

    @Override
    public void onSuccess(String s, BaseIModel.DataType dataType) {
        if (StringUtis.equals(s, UIUtils.getStringRes(R.string.pay_success))) {
            view.buySuccess();
            view.transfePageMsg(UIUtils.getStringRes(R.string.refresh_page));
        } else
            view.transfePageMsg(s);
        view.hideLoading();


    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
