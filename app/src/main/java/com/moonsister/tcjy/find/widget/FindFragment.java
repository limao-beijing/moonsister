package com.moonsister.tcjy.find.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pc on 2016/5/31.
 */
public class FindFragment extends BaseFragment {

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return UIUtils.inflateLayout(R.layout.fragment_find, container);
    }

    @Override
    protected void initData() {
    }

//R.id.relative_activi, R.id.relative_nearby,
    @OnClick({R.id.relative_video, R.id.relative_rank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_video:

                break;
//            case R.id.relative_activi:
//                break;
//            case R.id.relative_nearby:
//                ActivityUtils.startNearbyActivity();
//                break;
            case R.id.relative_rank://排行榜
                ActivityUtils.startRankActivity();
                break;
        }
    }
}
