package com.moonsister.tcjy.home.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.ChannelItem;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.widget.ColumnHorizontalScrollView;
import com.moonsister.tcjy.widget.ViewPager;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/24.
 */
public class HomeTopTagFragment extends BaseFragment {
    @Bind(R.id.mColumnHorizontalScrollView)
    ColumnHorizontalScrollView mColumnHorizontalScrollView;
    @Bind(R.id.mRadioGroup_content)
    LinearLayout mRadioGroup_content;
    @Bind(R.id.ll_more_columns)
    LinearLayout ll_more_columns;
    @Bind(R.id.rl_column)
    RelativeLayout rl_column;
    @Bind(R.id.mViewPager)
    ViewPager mViewPager;
    @Bind(R.id.button_more_columns)
    ImageView button_more_columns;


    /**
     * 用户选择的新闻分类列表
     */
    private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    /**
     * 当前选中的栏目
     */
    private int columnSelectIndex = 0;
    /**
     * 左阴影部分
     */
    public ImageView shade_left;
    /**
     * 右阴影部分
     */
    public ImageView shade_right;
    /**
     * 屏幕宽度
     */
    private int mScreenWidth = 0;
    /**
     * Item宽度
     */
    private int mItemWidth = 0;

    /**
     * 请求CODE
     */
    public final static int CHANNELREQUEST = 1;
    /**
     * 调整返回的RESULTCODE
     */
    public final static int CHANNELRESULT = 10;

    private EnumConstant.HomeTopFragmentTop fragmentType;

    public static HomeTopTagFragment newInstance() {
        return new HomeTopTagFragment();
    }

    public void setType(EnumConstant.HomeTopFragmentTop type) {
        this.fragmentType = type;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScreenWidth = ((WindowManager) container.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7
        return inflater.inflate(R.layout.fragment_home_top_tag, container, false);
    }

    @Override
    protected void initData() {
        setChangelView();
    }

    /**
     * 当栏目项发生变化时候调用
     */
    private void setChangelView() {
        initColumnData();
        initTabColumn();
        initFragment();
    }

    /**
     * 获取Column栏目 数据
     */
    private void initColumnData() {
        userChannelList.add(new ChannelItem(1, "1", 1, 1));
        userChannelList.add(new ChannelItem(1, "1", 1, 1));
        userChannelList.add(new ChannelItem(1, "1", 1, 1));
    }

    /**
     * 初始化Column栏目项
     */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = userChannelList.size();
        if (columnSelectIndex > count) {
            columnSelectIndex = 0;
        }
        mColumnHorizontalScrollView.setParam(getActivity(), mScreenWidth,
                mRadioGroup_content, shade_left, shade_right, ll_more_columns,
                rl_column);
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    mItemWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            // TextView localTextView = (TextView)
            // mInflater.inflate(R.layout.column_radio_item, null);
            TextView columnTextView = new TextView(getActivity());
            columnTextView.setTextAppearance(getActivity(),
                    R.style.top_category_scroll_view_item_text);
            // localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
            // columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 0);
            columnTextView.setId(i);
            Drawable drawable = getResources().getDrawable(R.drawable.dr_title_text_bottom_line_selector);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            columnTextView.setCompoundDrawables(null, null, null, drawable);
            columnTextView.setText(userChannelList.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(
                    R.color.top_category_scroll_text_color_day));
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else {
                            localView.setSelected(true);
                            if (mViewPager != null) {
                                mViewPager.setCurrentItem(i);
                            }
                        }
                    }
                    // Toast.makeText(getApplicationContext(),
                    // userChannelList.get(v.getId()).getName(),
                    // Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i, params);
        }
    }

    /**
     * 选择的Column里面的Tab
     */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        // 判断是否选中
        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
      /*  FragmentFactory.cleanFragments();
        // fragments.clear();// 清空
        // int count = userChannelList.size();
        // for (int i = 0; i < count; i++) {
        // Bundle data = new Bundle();
        // // data.putString("text", userChannelList.get(i).getName());
        // // data.putInt("id", userChannelList.get(i).getId());
        // // TVListFragment newfragment = new TVListFragment();
        // NewsFragment newfragment = new NewsFragment();
        // newfragment.setArguments(data);
        // fragments.add(newfragment);
        // }
        // NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(
        // activity.getSupportFragmentManager(), fragments);
        LogUtil.e(this, "****************mViewPager初始化***************");
        // if (mainAdapter == null) {
        mainAdapter = new MainAdapter(getChildFragmentManager());
        selectTab(0);
        mViewPager.setAdapter(mainAdapter);
//		mViewPager.setOffscreenPageLimit(0);
        mViewPager.setOnPageChangeListener(pageListener);
        // } else {
        // mainAdapter.notifyDataSetChanged();
        // }*/

    }
}
