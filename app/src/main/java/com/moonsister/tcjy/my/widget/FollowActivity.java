package com.moonsister.tcjy.my.widget;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.bean.FrientBaen;
import com.hickey.tool.activity.FragmentUtils;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.main.view.RelationActivityView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/24.
 */
public class FollowActivity extends BaseActivity implements RelationActivityView {
    @Bind(R.id.my_follow)//我关注的
    TextView my_follow;
    @Bind(R.id.follow_my)//关注我的
    TextView follow_my;
    @Bind(R.id.image_back)//返回键
    ImageView imageBack;
    @Bind(R.id.image_pingbi)//屏蔽
    ImageView image_pingbi;
    private ChatFollowFragment mChatFg;//我关注的fragment
    private ContactsFragment mContactsFg;//关注我的fragment
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

//    @Override
//    public void setPingBiData(PingbiBean pingBiBaen) {
//        setPingBiData(pingBiBaen);
//    }

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



    @OnClick({R.id.my_follow, R.id.follow_my,R.id.image_back,R.id.image_pingbi})

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
                Intent intent=new Intent(FollowActivity.this,PingbiActivity.class);
                startActivity(intent);
                break;
            case R.id.image_back:
                this.finish();

                break;
        }
    }

}

