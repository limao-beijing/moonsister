package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/6/27.
 */
public class MyAccountChongZFragment  extends BaseFragment{
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return UIUtils.inflateLayout(R.layout.fragment_my_account_chong_z,container);
    }

    @Override
    protected void initData() {

    }
}
