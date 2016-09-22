package com.moonsister.tcjy.engagement;

import android.support.v4.app.Fragment;

import com.moonsister.tcjy.base.BaseFragmentActivity;

/**
 * Created by jb on 2016/9/22.
 */
public class EengegamentRecommendActivity extends BaseFragmentActivity {

    @Override
    protected Fragment initFragment() {
        return EengegamentRecommendFragment.newInstance();
    }
}
