package com.moonsister.tcjy.utils;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by pc on 2016/5/31.
 */
public class FragmentUtils {
    /**
     * 选择隐藏的Fragment
     *
     * @param manager
     * @param layout
     * @param hide
     * @param show
     */
    public static void switchHideFragment(FragmentManager manager, int layout,
                                          Fragment hide, Fragment show) {

        if (show == null || manager == null)
            return;
        if (hide == null) {
            swichReplaceFramgent(manager, layout, show);
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        // FragmentTransaction transaction =mFm. getSupportFragmentManager()
        // .beginTransaction().setCustomAnimations(R.anim.tran_pre_in,
        // R.anim.tran_pre_out);
        if (!show.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.hide(hide).add(layout, show)
                    .commitAllowingStateLoss();
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(hide).show(show).commitAllowingStateLoss();
        }
    }

    /**
     * 替换Fragment
     *
     * @param manager
     * @param containerViewId
     * @param fragment
     */
    public static void swichReplaceFramgent(FragmentManager manager, @IdRes int containerViewId, Fragment fragment) {
        if (containerViewId == -1 || fragment == null || manager == null)
            return;
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(containerViewId, fragment);
        transaction.commitAllowingStateLoss();

    }
}
