package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.utils.EnumConstant;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/8/23.
 */
public class HomeTopFragment extends BaseFragment {
    @Bind(R.id.tv_home_hot)
    TextView tvHomeHot;
    @Bind(R.id.tv_home_nearby)
    TextView tvHomeNearby;
    @Bind(R.id.tv_home_new)
    TextView tvHomeNew;
    private HomeTopItemFragment homeHotFragment, homeNearbyFragment, honeNewFragment;
    private BaseFragment currFragment;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_top, container, false);
    }

    @Override
    protected void initData() {
        onClick(tvHomeHot);
    }

    @OnClick({R.id.iv_search, R.id.tv_home_hot, R.id.tv_home_nearby, R.id.tv_home_new})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:

                break;
            case R.id.tv_home_hot:
                if (homeHotFragment == null) {
                    homeHotFragment = HomeTopItemFragment.newInstance();
                    homeHotFragment.setType(EnumConstant.HomeTopFragmentTop.HOT);
                }
                switchNatvigationSelect(homeHotFragment);

                break;
            case R.id.tv_home_nearby:
                if (homeNearbyFragment == null) {
                    homeNearbyFragment = HomeTopItemFragment.newInstance();
                    homeNearbyFragment.setType(EnumConstant.HomeTopFragmentTop.NAERBY);
                }
                switchNatvigationSelect(homeNearbyFragment);
                break;
            case R.id.tv_home_new:
                if (honeNewFragment == null) {
                    honeNewFragment = HomeTopItemFragment.newInstance();
                    honeNewFragment.setType(EnumConstant.HomeTopFragmentTop.NEW);
                }
                switchNatvigationSelect(honeNewFragment);
                break;
        }

    }

    private void switchNatvigationSelect(BaseFragment fragment) {

        if (fragment == null)
            return;
        hideFragment(currFragment, fragment, R.id.fl_content);
        tvHomeHot.setSelected(fragment == homeHotFragment);
        tvHomeNearby.setSelected(fragment == homeNearbyFragment);
        tvHomeNew.setSelected(fragment == honeNewFragment);
        currFragment = fragment;

    }

}
