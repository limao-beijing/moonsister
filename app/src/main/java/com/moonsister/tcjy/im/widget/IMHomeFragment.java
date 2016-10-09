package com.moonsister.tcjy.im.widget;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.engagement.widget.EengegamentRecommendFragment;
import com.moonsister.tcjy.im.prsenter.IMHomeFragmentPresenter;
import com.moonsister.tcjy.utils.FragmentUtils;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by pc on 2016/5/31.
 */
public class IMHomeFragment extends BaseFragment {
    IMHomeFragmentPresenter presenter;
    @Bind(R.id.tv_navigation_good_select)
    TextView tvNavigationGoodSelect;
    @Bind(R.id.tv_navigation_same_city)
    TextView tvNavigationSameCity;
    @Bind(R.id.layout_home_content)
    FrameLayout frameLyoutHomeContent;

    @Bind(R.id.tv_navigation_same_rigtht)
    TextView tv_navigation_same_rigtht;
    private Fragment chatFragment, mCurrentFragment, engegamentRecommendFragment;
    private FrientFragment frientFragment;
    private Fragment conversationListFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        presenter = new IMHomeFragmentPresenterImpl();
//        presenter.attachView(this);
        View view = inflater.inflate(R.layout.home_one_menu, container, false);
        return view;
    }

    @Override
    protected void initData() {
        tvNavigationGoodSelect.setText(resources.getString(R.string.private_hat));
        tvNavigationSameCity.setText(resources.getString(R.string.hougong));
        tv_navigation_same_rigtht.setText(resources.getString(R.string.engagement));
        onClick(tvNavigationGoodSelect);
    }


    @OnClick({R.id.tv_navigation_good_select, R.id.tv_navigation_same_city, R.id.tv_navigation_same_rigtht})
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_navigation_good_select:
//                tvNavigationGoodSelect.setBackgroundResource(R.mipmap.my_foll);
//                tvNavigationGoodSelect.setTextColor(getResources().getColor(R.color.text_follow_color));
//                tvNavigationSameCity.setBackgroundResource(R.mipmap.my_follow);
//                tvNavigationSameCity.setTextColor(getResources().getColor(R.color.text_followmy_color));
//                break;
//            case R.id.tv_navigation_same_city:
//                tvNavigationGoodSelect.setBackgroundResource(R.mipmap.follow_my);
//                tvNavigationGoodSelect.setTextColor(getResources().getColor(R.color.text_followmy_color));
//                tvNavigationSameCity.setTextColor(getResources().getColor(R.color.text_follow_color));
//                tvNavigationSameCity.setBackgroundResource(R.mipmap.foll_my);
//                break;
//        }
//        ConversationListFragment instance = ConversationListFragment.getInstance();
        switch (view.getId()) {
            case R.id.tv_navigation_good_select:
                swith2PrivateChat();
                break;
            case R.id.tv_navigation_same_city:
                swith2Friend();
                break;
            case R.id.tv_navigation_same_rigtht:
                switchEngegament();
                break;
        }
        selectColor(view);
    }


    private void selectColor(View view) {
        Drawable drawable = getResources().getDrawable(R.drawable.shape_background_half_round_red);
        tvNavigationGoodSelect.setBackground(view == tvNavigationGoodSelect ? drawable : null);
        tvNavigationSameCity.setBackground(view == tvNavigationSameCity ? drawable : null);
        tv_navigation_same_rigtht.setBackground(view == tv_navigation_same_rigtht ? drawable : null);
    }


    public void swith2PrivateChat() {
        //启动会话列表界面
        if (chatFragment == null)
            chatFragment = ChatFragment.newInstance();
        enterPage(chatFragment);
    }

    public void swith2Friend() {
        if (frientFragment == null) {
            frientFragment = FrientFragment.newInstance();
            frientFragment.setPageType(FrientFragment.PAGE_WACTH);
        }
        enterPage(frientFragment);
    }

    private void switchEngegament() {
        if (engegamentRecommendFragment == null) {
            engegamentRecommendFragment = new EengegamentRecommendFragment();
        }
        enterPage(engegamentRecommendFragment);
    }


    /**
     * 进入
     *
     * @param fragment
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void enterPage(Fragment fragment) {
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
        tvNavigationGoodSelect.setSelected(fragment == chatFragment);
        tvNavigationSameCity.setSelected(fragment == frientFragment);

    }
}
