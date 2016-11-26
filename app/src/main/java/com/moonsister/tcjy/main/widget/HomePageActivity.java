package com.moonsister.tcjy.main.widget;

import android.support.v4.app.Fragment;

import com.hickey.tool.base.BaseFragmentActivity;


/**
 * Created by jb on 2016/9/1.
 */
public class HomePageActivity extends BaseFragmentActivity {


    @Override
    protected Fragment initFragment() {
        return HomePageFragment.newInstance();
    }

    @Override
    public boolean isShowTitleView() {
        return false;
    }
}
