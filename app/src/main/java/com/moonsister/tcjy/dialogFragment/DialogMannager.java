package com.moonsister.tcjy.dialogFragment;

import android.support.v4.app.FragmentManager;

import com.hickey.tool.base.BaseDialogFragment;
import com.moonsister.tcjy.dialogFragment.widget.BindPhoneDialogFragment;
import com.moonsister.tcjy.dialogFragment.widget.EngagementDelectDioalogFragment;
import com.moonsister.tcjy.dialogFragment.widget.EngagementPermissDialogFragment;
import com.moonsister.tcjy.dialogFragment.widget.ImPermissionDialog;
import com.moonsister.tcjy.dialogFragment.widget.InterestDialogFragment;
import com.moonsister.tcjy.dialogFragment.widget.SelectSexDialogFragment;

/**
 * Created by jb on 2016/11/3.
 */

public class DialogMannager {
    private static volatile DialogMannager instance;

    public static DialogMannager getInstance() {
        if (instance == null) {
            synchronized (DialogMannager.class) {
                if (instance == null) {
                    instance = new DialogMannager();
                }

            }
        }
        return instance;
    }

    /**
     * 选择男女
     *
     * @param manager
     */
    public void showSelectSexDialog(FragmentManager manager) {
        SelectSexDialogFragment sexDialogFragment = new SelectSexDialogFragment();
        sexDialogFragment.showDialogFragment(manager);
    }

    /**
     * 绑定手机
     *
     * @param manager
     */
    public void showBindPhoneDialog(FragmentManager manager) {
        BindPhoneDialogFragment fragment = new BindPhoneDialogFragment();
        fragment.showDialogFragment(manager);

    }

    /**
     * 选择兴趣
     *
     * @param manager
     */
    public void showInterestSelectDialog(FragmentManager manager) {
        InterestDialogFragment fragment = InterestDialogFragment.newInstance();
        fragment.showDialogFragment(manager);
    }

    /**
     * 显示聊天权限的弹框
     *
     * @param sex
     * @param manager
     */
    public void showImPermission(String sex, FragmentManager manager, ImPermissionDialog.OnCallBack onCallBack) {
        ImPermissionDialog dialog = ImPermissionDialog.newInstance(sex);
        dialog.showDialogFragment(manager);
        dialog.setOnCallBack(onCallBack);
    }

    /**
     * 显示约会权限的弹框
     *
     * @param manager
     */
    public void showEngagementPermission(String sex, FragmentManager manager, BaseDialogFragment.OnCallBack onCallBack) {
        EngagementPermissDialogFragment dialog = EngagementPermissDialogFragment.newInstance(sex);
        dialog.showDialogFragment(manager);
        dialog.setOnCallBack(onCallBack);
    }

    /**
     * 约会的权限
     *
     * @param manager
     * @param onCallBack
     */
    public void showEngaggementDialogFragment(FragmentManager manager, BaseDialogFragment.OnCallBack onCallBack) {
        EngagementDelectDioalogFragment fragment = EngagementDelectDioalogFragment.newInstance();
        fragment.showDialogFragment(manager);
        fragment.setOnCallBack(onCallBack);
    }


}
