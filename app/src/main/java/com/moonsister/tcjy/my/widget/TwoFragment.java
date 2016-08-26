package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/8/26.
 */
public class TwoFragment extends BaseFragment{
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return UIUtils.inflateLayout(R.layout.twofragment);
    }

    @Override
    protected void initData() {

    }
}
