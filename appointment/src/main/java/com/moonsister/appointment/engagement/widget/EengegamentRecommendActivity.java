package com.moonsister.appointment.engagement.widget;

import android.support.v4.app.Fragment;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragmentActivity;

/**
 * Created by jb on 2016/9/22.
 */
public class EengegamentRecommendActivity extends BaseFragmentActivity {
    @Override
    protected String initTitleName() {
        return getString(R.string.engagement);
    }

    @Override
    protected Fragment initFragment() {
        return EengegamentRecommendFragment.newInstance();
    }
}
