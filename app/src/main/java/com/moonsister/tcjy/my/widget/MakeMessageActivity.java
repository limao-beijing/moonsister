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
import butterknife.OnClick;

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

//    Fragment currentFragment;
//    private MakeFragment makefragment;
//    private MoneyFragment moneyfragment;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.makemessage);
    }

    @Override
    protected void initView() {
//        makefragment=new MakeFragment();
//        moneyfragment=new MoneyFragment();
//        FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,currentFragment,makefragment);
    }
    @OnClick({R.id.my_follow, R.id.follow_my,R.id.image_back})

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_follow:
//                FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,mContactsFg,mChatFg);
                my_follow.setBackgroundResource(R.mipmap.my_foll);
                my_follow.setTextColor(getResources().getColor(R.color.text_follow_color));
                follow_my.setBackgroundResource(R.mipmap.my_follow);
                follow_my.setTextColor(getResources().getColor(R.color.text_followmy_color));
                break;
            case R.id.follow_my:
//                FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,mChatFg,mContactsFg);
                my_follow.setBackgroundResource(R.mipmap.make_black);
                my_follow.setTextColor(getResources().getColor(R.color.text_followmy_color));
                follow_my.setTextColor(getResources().getColor(R.color.text_follow_color));
                follow_my.setBackgroundResource(R.mipmap.my_follow);
                break;
            case R.id.image_back:
                MakeMessageActivity.this.finish();
                break;
        }
    }
}
