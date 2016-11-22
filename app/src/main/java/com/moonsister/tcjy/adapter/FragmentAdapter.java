package com.moonsister.tcjy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by x on 2016/8/24.
 */
public class FragmentAdapter extends FragmentPagerAdapter{
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    public FragmentAdapter(FragmentManager fm,List<Fragment> mFragmentList) {
        super(fm);
        this.mFragmentList=mFragmentList;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
