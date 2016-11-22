package com.moonsister.tcjy.dialogFragment.widget;

import android.support.annotation.NonNull;
import android.view.View;

import com.moonsister.tcjy.R;
import com.hickey.tool.constant.EnumConstant;

import butterknife.OnClick;

/**
 * Created by jb on 2016/11/15.
 */

public class EngagementDelectDioalogFragment extends BaseDialogFragment {
    private ImPermissionDialog.OnCallBack mOnCallBack;

    @NonNull
    @Override
    protected int initViewId() {
        return R.layout.df_engagement_delect;
    }

    @Override
    protected void initData() {

    }

    public static EngagementDelectDioalogFragment newInstance() {
        return new EngagementDelectDioalogFragment();
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
}
