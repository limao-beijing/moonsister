package com.moonsister.tcjy.base;


import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.ButterKnife;
import im.gouyin.com.progressdialog.ProgressDialog;

/**
 * Created by pc on 2016/5/31.
 */
public abstract class BaseFragment extends BaseRxFragment implements View.OnTouchListener {
    protected View mRootView;
    private ProgressDialog progressDialog;
    protected Resources resources;
    protected Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        resources = getResources();
        mActivity = getActivity();
        mRootView = onBaseCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mRootView);
        initData();
        mRootView.setOnTouchListener(this);
        return mRootView;

    }

    protected void showToast(String msg) {
        UIUtils.showToast(getActivity(), msg);
    }


    protected View onBaseCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = initView(inflater, container, savedInstanceState);
        return mView;
    }

    /**
     * fragment 布局
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void onPause() {
        super.onPause();
        FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).hideSoftInput();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 初始化加载进度条
     */
    private void initProgressDialog() {
        progressDialog = new ProgressDialog(getActivity() == null ? ConfigUtils.getInstance().getActivityContext() : getActivity());
    }

    /**
     * 显示加载jindt
     */
    protected void showProgressDialog() {
        if (progressDialog == null)
            initProgressDialog();
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    /**
     * 隐藏加载进度条
     */
    protected void hideProgressDialog() {
        if (progressDialog == null)
            return;
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    /**
     * 替换fragment
     *
     * @param fragment
     */
    protected void replaceFramgent(Fragment fragment, @IdRes int resid) {
        if (fragment == null)
            return;
        FragmentUtils.swichReplaceFramgent(getChildFragmentManager(), resid, fragment);
    }

    /**
     * 隐藏fragment
     */
    protected void hideFragment(Fragment hide, Fragment show, @IdRes int resid) {
        if (hide == null || show == null)
            return;
        FragmentUtils.switchHideFragment(getChildFragmentManager(), resid, hide, show);
    }


}
