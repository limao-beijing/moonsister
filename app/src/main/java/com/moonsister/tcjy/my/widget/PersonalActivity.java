package com.moonsister.tcjy.my.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/8/25.
 */
public class PersonalActivity extends BaseActivity {

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.personalactivity);
    }

    @Override
    protected void initView() {

    }
}
