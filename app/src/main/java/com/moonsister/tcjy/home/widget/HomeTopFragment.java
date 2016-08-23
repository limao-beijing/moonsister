package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;

/**
 * Created by jb on 2016/8/23.
 */
public class HomeTopFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_top,container,false);
    }

    @Override
    protected void initData() {

    }
}
