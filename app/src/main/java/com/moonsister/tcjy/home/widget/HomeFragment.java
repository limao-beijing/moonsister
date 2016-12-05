package com.moonsister.tcjy.home.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.tool.activity.FragmentUtils;
import com.hickey.tool.base.BaseFragment;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.banner.BannerManager;
import com.moonsister.tcjy.home.presenetr.HomeFragmentPresenter;
import com.moonsister.tcjy.home.presenetr.HomeFragmentPresenterImpl;
import com.moonsister.tcjy.home.view.HomeView;
import com.moonsister.tcjy.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by pc on 2016/5/31.
 */
public class HomeFragment extends BaseFragment implements HomeView {

    @Bind(R.id.tv_navigation_good_select)
    TextView tvNavigationGoodSelect;
    @Bind(R.id.tv_navigation_same_city)
    TextView tvNavigationSameCity;
    @Bind(R.id.layout_home_content)
    FrameLayout layout_home_content;
    @Bind(R.id.tv_search)
    ImageView tv_search;
    @Bind(R.id.appx_banner_container)
    FrameLayout appx_banner_container;
    private HomeFragmentPresenter mPresenter;
    private FragmentManager mFragmentManager;
    private GoodSelectFragment mCurrentFragment, goodSelectFragment, sameCityFragment;
    private BannerManager bannerManager;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new HomeFragmentPresenterImpl(this);
        View view = inflater.inflate(R.layout.home_one_menu, container, false);
        return view;
    }


    @Override
    protected void initData() {
        tv_search.setVisibility(View.VISIBLE);
        mPresenter.swicthNavigation(R.id.tv_navigation_good_select);
        bannerManager = new BannerManager();
        bannerManager.showTopBanner(getActivity(), appx_banner_container);
    }


    @Override
    public void switch2GoodSelect() {
        if (goodSelectFragment == null) {
            goodSelectFragment = GoodSelectFragment.newInstance();
            goodSelectFragment.setPegeType(GoodSelectFragment.GOOD_SELECT);
        }
        enterPage(goodSelectFragment);

    }

    @Override
    public void switch2SameCity() {
        if (sameCityFragment == null) {
            sameCityFragment = GoodSelectFragment.newInstance();
            sameCityFragment.setPegeType(GoodSelectFragment.SAME_CITY);
        }
        enterPage(sameCityFragment);
    }

    @Override
    public void switch2Search() {
        ActivityUtils.startSearchActivity();
    }


    /**
     * 进入
     *
     * @param fragment
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void enterPage(GoodSelectFragment fragment) {
        if (fragment == null)
            return;
        switchNatvigationSelect(fragment);
        if (mFragmentManager == null)
            mFragmentManager = getChildFragmentManager();

        FragmentUtils.switchHideFragment(mFragmentManager, R.id.layout_home_content, mCurrentFragment, fragment);
        mCurrentFragment = fragment;
    }

    private void switchNatvigationSelect(Fragment fragment) {
        if (fragment == null)
            return;
        tvNavigationGoodSelect.setSelected(fragment == goodSelectFragment);
        tvNavigationSameCity.setSelected(fragment == sameCityFragment);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bannerManager != null)
            bannerManager = null;
    }

    @OnClick({R.id.tv_navigation_good_select, R.id.tv_navigation_same_city, R.id.tv_search})
    public void onClick(View view) {
        mPresenter.swicthNavigation(view.getId());
    }
}
