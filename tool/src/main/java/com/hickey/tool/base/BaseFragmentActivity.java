package com.hickey.tool.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.hickey.tool.activity.FragmentUtils;
import com.moonsister.tool.R;

/**
 * Created by jb on 2016/8/8.
 */
public abstract class BaseFragmentActivity extends BaseActivity {

    FrameLayout flBaseContent;
    protected Fragment currentFragment;

    @Override
    protected View setRootContentView() {

        return View.inflate(getApplicationContext(), R.layout.activity_base_fragment, null);

    }

    @Override
    protected void initView() {
        flBaseContent = (FrameLayout) findViewById(R.id.fl_base_content);
        FragmentUtils.swichReplaceFramgent(getSupportFragmentManager(), flBaseContent.getId(), initFragment());
        initData();
    }

    protected void initData() {
    }

    protected abstract Fragment initFragment();

    /**
     * 替换fragment
     *
     * @param f
     */
    protected void replaceFramgent(Fragment f) {
        if (f == null)
            return;
        FragmentUtils.swichReplaceFramgent(getSupportFragmentManager(), flBaseContent.getId(), f);
        currentFragment = f;
    }

    /**
     * 替换fragment
     *
     * @param f
     */
    protected void replaceFramgent(Fragment f, @IdRes int resid) {
        if (f == null)
            return;
        FragmentUtils.swichReplaceFramgent(getSupportFragmentManager(), resid, f);
        currentFragment = f;
    }

    /**
     * 隐藏fragment
     *
     * @param f
     */
    protected void hideFragment(Fragment f) {
        if (f == null)
            return;
        FragmentUtils.switchHideFragment(getSupportFragmentManager(), flBaseContent.getId(), currentFragment, f);
        currentFragment = f;
    }

    /**
     * 隐藏fragment
     *
     * @param f
     */
    protected void hideFragment(Fragment f, @IdRes int resid) {
        if (f == null)
            return;
        FragmentUtils.switchHideFragment(getSupportFragmentManager(), resid, currentFragment, f);
        currentFragment = f;
    }


}
