package com.moonsister.tcjy.my.widget;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;

/**
 * Created by x on 2016/8/24.
 */
public class MakeMessageActivity extends BaseActivity{
    @Bind(R.id.my_follow)
    TextView my_follow;
    @Bind(R.id.follow_my)
    TextView follow_my;
    @Bind(R.id.image_back)
    ImageView imageBack;

    Fragment currentFragment;
    private MakeFragment makefragment;
    private MoneyFragment moneyfragment;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.makemessage);
    }

    @Override
    protected void initView() {
        makefragment=new MakeFragment();
        moneyfragment=new MoneyFragment();
        FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,currentFragment,makefragment);
    }
}
