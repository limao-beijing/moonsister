package com.moonsister.tcjy.dialogFragment.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.EngagementPermissTextBane;
import com.moonsister.tcjy.dialogFragment.presenter.EngagementPermissPersenter;
import com.moonsister.tcjy.dialogFragment.presenter.EngagementPermissPersenterImpl;
import com.moonsister.tcjy.dialogFragment.view.EngagementPermissView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tool.lang.StringUtis;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/11/13.
 */

public class EngagementPermissDialogFragment extends BaseDialogFragment implements EngagementPermissView {
    @Bind(R.id.tv)
    TextView mTv;
    @Bind(R.id.tv_vip_rule)
    TextView mTvVipRule;
    @Bind(R.id.tv_upgrade_vip)
    TextView tv_upgrade_vip;
    private ImPermissionDialog.OnCallBack mOnCallBack;
    private EngagementPermissPersenter persenter;

    @NonNull
    @Override
    protected int initViewId() {
        return R.layout.df_engagement_permiss;
    }

    @Override
    protected void initData() {
        String sex = getArguments().getString("sex");
        if (StringUtis.equals(sex,"1")){
            tv_upgrade_vip.setText(getText(R.string.upgrade_vip));
        }else {
            tv_upgrade_vip.setText(getText(R.string.upgrade_renzheng));
        }
        persenter = new EngagementPermissPersenterImpl();
        persenter.attachView(this);
        persenter.loadTextMsg();

    }

    public static EngagementPermissDialogFragment newInstance(String sex) {
        EngagementPermissDialogFragment fragment = new EngagementPermissDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sex", sex);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setOnCallBack(ImPermissionDialog.OnCallBack onCallBack) {
        mOnCallBack = onCallBack;
    }


    @OnClick({R.id.tv_to_do_engagement, R.id.tv_upgrade_vip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_to_do_engagement:
                if (mOnCallBack != null)
                    mOnCallBack.onStatus(this, EnumConstant.DialogCallBack.CANCEL);
                break;
            case R.id.tv_upgrade_vip:
                if (mOnCallBack != null)
                    mOnCallBack.onStatus(this, EnumConstant.DialogCallBack.CONFIRM);
                break;
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
    public void setTextMsg(EngagementPermissTextBane bean) {
        mTv.setText(bean.getData().getTop_info());
        mTvVipRule.setText(bean.getData().getInfo());
    }
}
