package com.moonsister.tcjy.dialogFragment;

import android.support.v4.app.FragmentManager;

import com.moonsister.tcjy.dialogFragment.widget.BindPhoneDialogFragment;
import com.moonsister.tcjy.dialogFragment.widget.EngagementPermissDialogFragment;
import com.moonsister.tcjy.dialogFragment.widget.ImPermissionDialog;
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
     * 显示权限的弹框
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
     * 显示权限的弹框
     *

     * @param manager
     */
    public void showEngagementPermission(FragmentManager manager, ImPermissionDialog.OnCallBack onCallBack) {
        EngagementPermissDialogFragment dialog = EngagementPermissDialogFragment.newInstance();
        dialog.showDialogFragment(manager);
        dialog.setOnCallBack(onCallBack);
    }
}
