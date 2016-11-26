package com.hyphenate.easeui.ui;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.easemob.easeui.R;
import com.hickey.tool.base.BaseDialogFragment;
import com.hickey.tool.constant.EnumConstant;
import com.hyphenate.easeui.CustomConstant;
import com.hyphenate.easeui.mvp.presenter.ChargeMessageDialogPresenter;
import com.hyphenate.easeui.mvp.presenter.ChargeMessageDialogPresenterImpl;
import com.hyphenate.easeui.mvp.view.ChargeMessageDialogView;

/**
 * Created by jb on 2016/11/25.
 */

public class ChargeMessageDialog extends BaseDialogFragment implements View.OnClickListener, ChargeMessageDialogView {
    private TextView tvMoney;
    private ChargeMessageDialogPresenter presenter;
    private String lid;
    private String acthcode;

    @NonNull
    @Override
    protected int initViewId() {
        return R.layout.df_charge_pay;
    }

    @Override
    protected void initData() {
        String money = getArguments().getString(CustomConstant.ESSAGE_ATTRIBUTE_MONEY);
        lid = getArguments().getString(CustomConstant.ESSAGE_ATTRIBUTE_LID);
        acthcode = getArguments().getString(CustomConstant.ESSAGE_ATTRIBUTE_ACTHCODE);
        tvMoney = (TextView) mRootView.findViewById(R.id.tv_money);
        tvMoney.setText("￥ " + money);
        mRootView.findViewById(R.id.tv_submit).setOnClickListener(this);
        mRootView.findViewById(R.id.iv_cancel).setOnClickListener(this);
        presenter = new ChargeMessageDialogPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_submit) {
            presenter.pay(getActivity(), EnumConstant.PayType.IAPP_PAY, lid, acthcode);
        } else if (i == R.id.iv_cancel) {
            dismissDialogFragment();
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void setSuccess() {
        if (mOnCallBack != null) {
            mOnCallBack.onStatus(this, EnumConstant.DialogCallBack.CONFIRM);
        }
    }
}
