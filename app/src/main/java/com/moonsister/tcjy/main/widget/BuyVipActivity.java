package com.moonsister.tcjy.main.widget;

import android.support.v4.app.Fragment;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragmentActivity;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/8/12.
 */
public class BuyVipActivity  extends BaseFragmentActivity{
    @Override
    protected Fragment initFragment() {
        return BuyVipFragment.newInstance();
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.appbar_vip);
    }
}
