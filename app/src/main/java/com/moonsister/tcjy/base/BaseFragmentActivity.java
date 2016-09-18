package com.moonsister.tcjy.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/8.
 */
public abstract class BaseFragmentActivity extends BaseActivity {

    @Bind(R.id.fl_base_content)
    FrameLayout flBaseContent;
    protected Fragment currentFragment;

    @Override
    protected View setRootContentView() {

        return UIUtils.inflateLayout(R.layout.activity_base_fragment);
    }

    @Override
    protected void initView() {
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
