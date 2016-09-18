package com.moonsister.tcjy.main.widget;

import android.support.v4.app.Fragment;

import com.moonsister.tcjy.base.BaseFragmentActivity;


/**
 * Created by jb on 2016/8/12.
 */
public class BuyVipActivity extends BaseFragmentActivity {
    @Override
    protected Fragment initFragment() {
        return BuyVipWebViewFragment.newInstance();
    }

}
