package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.banner.BannerManager;
import com.moonsister.tcjy.utils.ActivityUtils;

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
    @Bind(R.id.fl_banner)
    ViewGroup flBanner;
    private HomeTopItemFragment homeHotFragment, homeNearbyFragment, honeNewFragment;
    private BannerManager bannerManager;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_top, container, false);
    }

    @Override
    protected void initData() {
        onClick(tvHomeHot);
        bannerManager = new BannerManager();
        bannerManager.start(getActivity(), flBanner);
    }

    @Override
    public void onStart() {
        super.onStart();
//        UserInfoBannerManager.getInstance().show(getActivity(), flBanner);
    }

    @OnClick({R.id.iv_search, R.id.tv_home_hot, R.id.tv_home_nearby, R.id.tv_home_new})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                ActivityUtils.startSearchFragmentActivity();
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
        hideFragment(fragment, R.id.fl_content);
        tvHomeHot.setSelected(fragment == homeHotFragment);
        tvHomeNearby.setSelected(fragment == homeNearbyFragment);
        tvHomeNew.setSelected(fragment == honeNewFragment);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bannerManager != null)
            bannerManager = null;
    }
}
