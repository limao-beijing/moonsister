package com.moonsister.tcjy.feelingSquare.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by Administrator on 2016/9/20.
 */
public class FeelingSquareFragment extends BaseFragment {

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return   UIUtils.inflateLayout(R.layout.fragment_feelingsquare, container);
    }

    @Override
    protected void initData() {

    }

}
