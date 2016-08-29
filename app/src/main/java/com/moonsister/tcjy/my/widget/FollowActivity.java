package com.moonsister.tcjy.my.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.FrientBaen;
import com.moonsister.tcjy.main.view.RelationActivityView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/24.
 */
public class FollowActivity extends BaseActivity implements RelationActivityView {
    @Bind(R.id.my_follow)
    TextView my_follow;
    @Bind(R.id.follow_my)
    TextView follow_my;
    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.image_pingbi)
    ImageView image_pingbi;
    private ChatFollowFragment mChatFg;
    private ContactsFragment mContactsFg;
    Fragment currentFragment;

    @Override
    protected View setRootContentView() {

        return UIUtils.inflateLayout(R.layout.follow);
    }

    @Override
    protected void initView() {
        mChatFg = new ChatFollowFragment();
        mChatFg.setUid(getIntent().getStringExtra("uid"));
        mContactsFg = new ContactsFragment();

        FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,currentFragment,mChatFg);

    }

    @Override
    public void setFrientData(FrientBaen frientBaen) {
        setFrientData(frientBaen);
    }

    @Override
    public void showLoading() {
        showLoading();
    }

    @Override
    public void hideLoading() {
        hideLoading();
    }

    @Override
    public void transfePageMsg(String msg) {
        transfePageMsg(msg);
    }



    @OnClick({R.id.my_follow, R.id.follow_my,R.id.image_back})

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_follow:
                FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,mContactsFg,mChatFg);
                my_follow.setBackgroundResource(R.mipmap.my_foll);
                my_follow.setTextColor(getResources().getColor(R.color.text_follow_color));
                follow_my.setBackgroundResource(R.mipmap.my_follow);
                follow_my.setTextColor(getResources().getColor(R.color.text_followmy_color));
                image_pingbi.setVisibility(View.INVISIBLE);
                break;
            case R.id.follow_my:
                FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,mChatFg,mContactsFg);
                my_follow.setBackgroundResource(R.mipmap.follow_my);
                my_follow.setTextColor(getResources().getColor(R.color.text_followmy_color));
                follow_my.setTextColor(getResources().getColor(R.color.text_follow_color));
                follow_my.setBackgroundResource(R.mipmap.foll_my);
                image_pingbi.setVisibility(View.VISIBLE);
                break;
            case R.id.image_pingbi:

                break;
            case R.id.image_back:
                FollowActivity.this.finish();

                break;
        }
    }


//  @OnClick({R.id.my_follow,R.id.follow_my})
//    public void onClick(View v) {
//        int vId = v.getId();
//
//        switch(vId){
//            case R.id.my_follow:
//                my_follow.setBackgroundResource(R.mipmap.my_foll);
//                my_follow.setTextColor(getResources().getColor(R.color.text_follow_color));
//                follow_my.setBackgroundResource(R.mipmap.my_follow);
//                follow_my.setTextColor(getResources().getColor(R.color.text_followmy_color));
//                break;
//            case R.id.follow_my:
//                my_follow.setBackgroundResource(R.mipmap.my_foll);
//                my_follow.setTextColor(getResources().getColor(R.color.text_followmy_color));
//                follow_my.setTextColor(getResources().getColor(R.color.text_follow_color));
//                follow_my.setBackgroundResource(R.mipmap.my_follow);
//                break;
//        }
//    }

}

