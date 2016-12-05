package com.hickey.tool.base;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.hickey.tool.ConfigUtils;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.log.L;
import com.hickey.tool.manager.DeviceManager;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tool.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.ButterKnife;
import im.gouyin.com.progressdialog.ProgressDialog;


/**
 * Created by jb on 2016/5/30.
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    protected String TAG = this.getClass().getSimpleName();
    protected View mRootView;
    private ProgressDialog progressDialog;
    protected View titleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isAddActivity())
            ConfigUtils.getInstance().addActivity(this);
        onBaseCreate(savedInstanceState);
        mRootView = setRootContentView();
        titleView = mRootView.findViewById(R.id.app_title_bar);
        if (titleView != null)
            if (isShowTitleView()) {
                initTitieBar(titleView);
            } else
                titleView.setVisibility(View.GONE);


        if (mRootView == null) {
            if (L.getDeBugState())
                throw new RuntimeException("root layout not null");
            else
                return;
        }
        setContentView(mRootView);

        ButterKnife.bind(this);
        initView();
        // 方向锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        onBaseStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onBaseResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onBasePause();
        if (isFinishing()) {
            hideSoftInput();
        }
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        onBaseStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onBaseDestroy();
        ButterKnife.unbind(this);
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
            }
            progressDialog.dismiss();
            progressDialog = null;
        }
        ConfigUtils.getInstance().removeActivity(this);
    }

    public boolean isShowTitleView() {
        return true;
    }

    public void showToast(String msg) {
        UIUtils.showToast(this, msg);
    }

    private void initTitieBar(View view) {
        this.titleView = view;
        view.findViewById(R.id.action_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView) view.findViewById(R.id.tv_title_name)).setText(initTitleName());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideSoftInput();
        return super.onTouchEvent(event);
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftInput() {
        DeviceManager.hideSoftInput(this, getCurrentFocus());
    }

    public void showSoftInput(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    /**
     * 初始化title名字
     *
     * @return
     */
    protected String initTitleName() {
        return "标题";
    }

    /**
     * 设置title名字
     *
     * @return
     */
    public void setTitleName(String titleName) {
        if (titleView == null)
            return;
        ((TextView) titleView.findViewById(R.id.tv_title_name)).setText(titleName);
    }

    /**
     * 返回titleView
     *
     * @return titleView
     */
    protected View getTitleView() {
        return titleView;
    }

    /**
     * 设置根布局
     *
     * @return 根布局
     */
    protected abstract View setRootContentView();

    /**
     * 初始化布局数据
     */
    protected abstract void initView();


    /**
     * super.onDestroy();子类继承
     *
     * @param savedInstanceState
     */
    protected void onBaseCreate(Bundle savedInstanceState) {
    }

    /**
     * 子类继承
     */
    protected void onBaseStart() {
    }

    /**
     * 子类继承
     */
    protected void onBaseResume() {
    }

    /**
     * 子类继承
     */
    protected void onBasePause() {
    }

    /**
     * 子类继承
     */
    protected void onBaseStop() {
    }

    /**
     * 子类继承
     */
    protected void onBaseDestroy() {
    }

    /**
     * 初始化加载进度条
     */
    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this) {
            @Override
            public String getProgressDialogMsg() {
                String s = initProgressDialogMsg();
                if (!StringUtis.isEmpty(s))
                    return s;
                return super.getProgressDialogMsg();
            }
        };
    }

    protected String initProgressDialogMsg() {
        return null;
    }

    ;

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
        progressDialog = null;
    }


    public boolean isBaseonActivityResult() {
        return true;
    }

    /**
     * 更新界面
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (isBaseonActivityResult()) {
            FragmentManager fm = getSupportFragmentManager();
            int index = requestCode >> 16;
            if (index != 0) {
                index--;
                if (fm.getFragments() == null || index < 0
                        || index >= fm.getFragments().size()) {
                    L.e(TAG,
                            "Activity result fragment index out of range: 0x"
                                    + Integer.toHexString(requestCode));
                    return;
                }
                Fragment frag = fm.getFragments().get(index);
                if (frag == null) {
                    L.e(TAG,
                            "Activity result no fragment exists for index: 0x"
                                    + Integer.toHexString(requestCode));
                } else {
                    handleResult(frag, requestCode, resultCode, data);
                }
                return;
            }
        }
    }

    /**
     * 递归调用，对所有子Fragement生效
     *
     * @param frag
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment frag, int requestCode, int resultCode,
                              Intent data) {
        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    handleResult(f, requestCode, resultCode, data);
            }
        }
    }


    public boolean isAddActivity() {
        return true;
    }


    public void showLoading() {
        showProgressDialog();
    }


    public void hideLoading() {
        hideProgressDialog();
    }


    public void transfePageMsg(String msg) {
        showToast(msg);
    }
}
