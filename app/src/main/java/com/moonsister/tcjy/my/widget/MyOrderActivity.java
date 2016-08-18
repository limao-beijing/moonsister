package com.moonsister.tcjy.my.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/27.
 */
public class MyOrderActivity  extends BaseActivity{



    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.tv_all)
    TextView tvAll;
    @Bind(R.id.tv_prepare)
    TextView tvPrepare;
    @Bind(R.id.tv_underway)
    TextView tvUnderWay;
    @Bind(R.id.tv_finnish)
    TextView tvFinish;
    @Bind(R.id.tv_refund)
    TextView tvRefund;

    List<Fragment> fragmentList;
    private BaseFragment allFragment;
    private BaseFragment finishFragment;
    private BaseFragment preparePayFragment;
    private BaseFragment refundFragment;
    private BaseFragment underWayFragment;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_my_order);
    }

    @Override
    protected String initTitleName() {
        return super.initTitleName();
    }

    @Override
    protected void initView() {

        fragmentList = new ArrayList<Fragment>();
        allFragment = AllFragment.newInstance (R.layout.fragment_all);
        finishFragment = AllFragment.newInstance (R.layout.fragment_all);
        preparePayFragment =  AllFragment.newInstance (R.layout.fragment_all);
        refundFragment =  AllFragment.newInstance (R.layout.fragment_all);
        underWayFragment =  AllFragment.newInstance (R.layout.fragment_all);

        fragmentList.add(allFragment);
        fragmentList.add(preparePayFragment);
        fragmentList.add(underWayFragment);
        fragmentList.add(finishFragment);
        fragmentList.add(refundFragment);


        tvAll.setSelected(true);
        viewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                changeView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    class MyFrageStatePagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public MyFrageStatePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(getSupportFragmentManager());
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            if (fragmentList == null) {
                return 0;
            } else {
                return fragmentList.size();
            }
        }

    }
    public void changeView(int currentItem) {
        switch (currentItem) {
            case 0:
                tvAll.setSelected(true);
                tvPrepare.setSelected(false);
                tvUnderWay.setSelected(false);
                tvFinish.setSelected(false);
                tvRefund.setSelected(false);
                break;
            case 1:
                tvAll.setSelected(false);
                tvPrepare.setSelected(true);
                tvUnderWay.setSelected(false);
                tvFinish.setSelected(false);
                tvRefund.setSelected(false);
                break;
            case 2:
                tvAll.setSelected(false);
                tvPrepare.setSelected(false);
                tvUnderWay.setSelected(true);
                tvFinish.setSelected(false);
                tvRefund.setSelected(false);
                break;
            case 3:
                tvAll.setSelected(false);
                tvPrepare.setSelected(false);
                tvUnderWay.setSelected(false);
                tvFinish.setSelected(true);
                tvRefund.setSelected(false);
                break;
            case 4:
                tvAll.setSelected(false);
                tvPrepare.setSelected(false);
                tvUnderWay.setSelected(false);
                tvFinish.setSelected(false);
                tvRefund.setSelected(true);
                break;
        }
    }

    @OnClick({R.id.tv_all, R.id.tv_prepare, R.id.tv_underway, R.id.tv_finnish, R.id.tv_refund,R.id.action_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                viewPager.setCurrentItem(0);
                break;

            case R.id.tv_prepare:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_underway:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_finnish:
                viewPager.setCurrentItem(3);
                break;
            case R.id.tv_refund:
                viewPager.setCurrentItem(4);
                break;
            case R.id.action_back:
                finish();
                break;

        }
    }

}
