package com.hyphenate.easeui.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.easemob.easeui.R;
import com.hickey.tool.base.BaseDialogFragment;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.js.WebActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
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
    private EMMessage message;

    @NonNull
    @Override
    protected int initViewId() {
        return R.layout.df_charge_pay;
    }

    @Override
    protected void initData() {
        String money = "";
        try {
            message = getArguments().getParcelable(CustomConstant.ESSAGE_ATTRIBUTE_EMMESSAGE);
            lid = message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LID, "");
            money = message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_MONEY, "");
        } catch (Exception e) {
        }
        acthcode = getArguments().getString(CustomConstant.ESSAGE_ATTRIBUTE_ACTHCODE);
        tvMoney = (TextView) mRootView.findViewById(R.id.tv_money);
        tvMoney.setText("￥ " + money);
        mRootView.findViewById(R.id.tv_submit).setOnClickListener(this);
        mRootView.findViewById(R.id.iv_cancel).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_rule).setOnClickListener(this);
        presenter = new ChargeMessageDialogPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_submit) {
            presenter.pay(getActivity(), EnumConstant.PayType.IAPP_PAY, lid, acthcode);
        } else if (i == R.id.iv_cancel) {
            if (mOnCallBack != null) {
                mOnCallBack.onStatus(this, EnumConstant.DialogCallBack.CANCEL);
            }
            dismissDialogFragment();
        } else if (i == R.id.tv_rule) {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("url", "http://module.chuse.hk/index.php/index/chat/html?authcode=" + acthcode);
            getActivity().startActivity(intent);
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
    public void setSuccess(Long rxpire) {
        if (mOnCallBack != null) {
            message.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_EXPIRE_TIME, System.currentTimeMillis() + rxpire * 1000);
            message.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LOOK, true);
            EMClient.getInstance().chatManager().updateMessage(message);
            mOnCallBack.onStatus(this, EnumConstant.DialogCallBack.CONFIRM);
        }
    }
}
