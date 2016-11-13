package com.moonsister.tcjy.dialogFragment.widget;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.ButterKnife;
import im.gouyin.com.progressdialog.ProgressDialog;

/**
 * Created by jb on 2016/11/2.
 */

public abstract class BaseDialogFragment extends AppCompatDialogFragment implements View.OnTouchListener {
    private View mRootView;
    protected Resources mResources;
    protected Activity mActivity;
    private ProgressDialog progressDialog;
    private OnDismissListener mOnDismissListener;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_Fragment);
        mResources = getResources();
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(initViewId(), container, false);
        ButterKnife.bind(this, mRootView);
        mRootView.setOnTouchListener(this);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    /**
     * 布局
     *
     * @return
     */
    @LayoutRes
    @NonNull
    protected abstract int initViewId();

    /**
     * 初始数据
     */
    protected abstract void initData();

    private FragmentManager fragmentManager;

    public void showDialogFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        //这里直接调用show方法会报java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        //因为show方法中是通过commit进行的提交(通过查看源码)
        //这里为了修复这个问题，使用commitAllowingStateLoss()方法
        //注意：DialogFragment是继承自android.app.Fragment，这里要注意同v4包中的Fragment区分，别调用串了
        //DialogFragment有自己的好处，可能也会带来别的问题
        //dialog.show(getFragmentManager(), "SignDialog");
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(this, getClass().getName());
        ft.commitAllowingStateLoss();
        setCancelable(false);
    }

    public void dismissDialogFragment() {
//        dismiss();
        dismissAllowingStateLoss();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mActivity != null && mActivity instanceof BaseActivity) {
            ((BaseActivity) mActivity).hideSoftInput();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mOnDismissListener != null) {
            if (fragmentManager != null) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.remove(this);
                transaction.commit();
            }
            mOnDismissListener.onDismiss();
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mActivity != null && mActivity instanceof BaseActivity) {
            ((BaseActivity) mActivity).hideSoftInput();
        }
        return false;
    }

    protected void showToast(String msg) {
        UIUtils.showToast(getActivity(), msg);
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

}
