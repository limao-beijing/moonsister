package com.moonsister.tcjy.my.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/8/26.
 */
public class AddActivity extends BaseActivity{
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_add);
    }

    @Override
    protected void initView() {

    }
}
