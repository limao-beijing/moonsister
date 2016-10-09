package com.moonsister.tcjy.home.widget;

import android.support.v4.app.Fragment;
import android.view.View;

import com.moonsister.tcjy.base.BaseFragmentActivity;
import com.moonsister.tcjy.find.widget.OnlineFragment;
import com.moonsister.tcjy.utils.StringUtis;

/**
 * Created by jb on 2016/10/9.
 */

public class HomeSearchActivity extends BaseFragmentActivity {
    private HomeThreeFragment mHomeThreeFragment;
    private OnlineFragment onlineFragment;

    @Override
    protected Fragment initFragment() {
        View view = getTitleView();
        if (view != null)
            view.setVisibility(View.GONE);
        String type = getIntent().getStringExtra("type");
        if (StringUtis.equals(type, "1")) {
            mHomeThreeFragment = new HomeThreeFragment();
            mHomeThreeFragment.setFlag(0);
            return mHomeThreeFragment;
        } else {
            onlineFragment = new OnlineFragment();
            onlineFragment.setFlag(0);
            return onlineFragment;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();


        String type = getIntent().getStringExtra("type");
        if (StringUtis.equals(type, "1")) {
            if (mHomeThreeFragment != null)
                mHomeThreeFragment.setShowSearchHeader(true);
        } else {
            if (onlineFragment != null)
                onlineFragment.setShowSearchHeader(true);
        }
    }

}
