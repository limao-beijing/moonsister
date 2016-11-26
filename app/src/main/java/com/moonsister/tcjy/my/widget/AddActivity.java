package com.moonsister.tcjy.my.widget;

import android.view.View;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;

/**
 * Created by x on 2016/8/26.
 */
public class AddActivity extends BaseActivity {
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_add);
    }

    @Override
    protected void initView() {

    }
}
