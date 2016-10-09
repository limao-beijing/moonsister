package com.moonsister.tcjy.main.widget;

import android.support.v4.app.Fragment;

import com.moonsister.tcjy.base.BaseFragmentActivity;

/**
 * Created by jb on 2016/9/29.
 */

public class PersonThreeActivity extends BaseFragmentActivity {
    @Override
    protected Fragment initFragment() {
        return new PersonThreeFragment();
    }

    @Override
    protected String initTitleName() {
        return getIntent().getStringExtra("name");
    }
}
