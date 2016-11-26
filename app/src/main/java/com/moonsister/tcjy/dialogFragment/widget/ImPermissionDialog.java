package com.moonsister.tcjy.dialogFragment.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.hickey.tool.base.BaseDialogFragment;
import com.moonsister.tcjy.R;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/11/7.
 */

public class ImPermissionDialog extends BaseDialogFragment {
    @Bind(R.id.not_certification)
    TextView mNotCertification;
    @Bind(R.id.certification_text)
    TextView mCertificationText;
    @Bind(R.id.confirm_vip)
    TextView confirm_vip;
    private String sex;

    @NonNull
    @Override
    protected int initViewId() {
        return R.layout.df_im_permission;
    }

    public static ImPermissionDialog newInstance(String sex) {
        ImPermissionDialog dialog = new ImPermissionDialog();
        Bundle bundle = new Bundle();
        bundle.putString("sex", sex);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    protected void initData() {
        sex = getArguments().getString("sex");
        if (StringUtis.equals(sex, "1")) {
            mNotCertification.setText(getString(R.string.none_permission, "VIP会员"));
            mCertificationText.setText(getString(R.string.im_certification_text, "开通VIP"));
            confirm_vip.setText(getString(R.string.confirm_im_vip_text));
        } else {
            mNotCertification.setText(getString(R.string.none_permission, "认证会员"));
            mCertificationText.setText(getString(R.string.im_certification_text, "完成资料认证"));
            confirm_vip.setText(getString(R.string.confirm_im_renzheng_text));
        }


    }



    @OnClick({R.id.cancel, R.id.confirm_vip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                if (mOnCallBack != null) {
                    mOnCallBack.onStatus(this, EnumConstant.DialogCallBack.CANCEL);
                }
                dismissDialogFragment();
                break;
            case R.id.confirm_vip:
                if (mOnCallBack != null) {
                    mOnCallBack.onStatus(this, EnumConstant.DialogCallBack.CONFIRM);
                }
                break;
        }
    }

}
