package com.moonsister.tcjy.my.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.persenter.WithdRawDepositPresenter;
import com.moonsister.tcjy.my.persenter.WithdRawDepositPresenterImpl;
import com.moonsister.tcjy.my.view.WithdRawDepositView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/27.
 */
public class WithdRawDepositActivity extends BaseActivity implements WithdRawDepositView {

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.tv_tixian)
    TextView tvTiXian;
    @Bind(R.id.tv_enable_money)
    TextView tv_enable_money;
    @Bind(R.id.tv_chongzhi)
    TextView tvChongZhi;
    private MyAccountChongZFragment chongZFragment;
    private MyAccountTiXianFragment tiXianFragment;
    private List<Fragment> fragmentList;
    private WithdRawDepositPresenter presenter;

    @Override
    protected View setRootContentView() {
        presenter = new WithdRawDepositPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_my_account);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.withdraw_deposit);
    }

    @Override
    protected void initView() {

        fragmentList = new ArrayList<Fragment>();
        chongZFragment = new MyAccountChongZFragment();
        tiXianFragment = new MyAccountTiXianFragment();


        fragmentList.add(tiXianFragment);
        fragmentList.add(chongZFragment);

        tvTiXian.setSelected(true);
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
        presenter.loadEnableMoney();
        setRx();
    }

    private void setRx() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.MONEY_CHANGE)
                .onNext(events -> {
                    presenter.loadEnableMoney();
                })
                .create();
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void setloadEnableMoney(String str) {
        tv_enable_money.setText(UIUtils.getStringRes(R.string.enable_money) + " : " + str);
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
                tvChongZhi.setSelected(false);
                tvTiXian.setSelected(true);
                break;
            case 1:
                tvChongZhi.setSelected(true);
                tvTiXian.setSelected(false);
                break;

        }


    }

    @OnClick({R.id.tv_chongzhi, R.id.tv_tixian, R.id.get_money})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_chongzhi:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_tixian:
                viewPager.setCurrentItem(0);
                break;
            case R.id.get_money:
                ActivityUtils.startGetMoneyActivity();
                break;
        }
    }
}
