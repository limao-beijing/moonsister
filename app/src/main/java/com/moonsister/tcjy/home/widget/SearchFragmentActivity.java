package com.moonsister.tcjy.home.widget;

import android.support.v4.app.Fragment;
import android.view.View;

import com.hickey.tool.base.BaseFragmentActivity;


/**
 * Created by jb on 2016/8/26.
 */
public class SearchFragmentActivity extends BaseFragmentActivity {
    @Override
    protected Fragment initFragment() {
        titleView.setVisibility(View.GONE);
        return SearchFragment.newInstance();
    }

}
