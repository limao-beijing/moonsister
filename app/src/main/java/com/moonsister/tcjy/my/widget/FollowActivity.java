package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.FrientBaen;
import com.moonsister.tcjy.main.presenter.RelationActivityPresenterImpl;
import com.moonsister.tcjy.main.view.RelationActivityView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/22.
 */
public class FollowActivity extends BaseActivity implements View.OnClickListener, RelationActivityView {
    @Bind(R.id.image_back)
    ImageView image_back;//关注页面的返回键
    @Bind(R.id.my_follow)//我关注的按钮
            TextView my_follow;
    @Bind(R.id.follow_my)//关注我的按钮
            TextView follow_my;
    RelationActivityPresenterImpl presenter;
//    @Bind(R.id.image_back)
//    ImageView imageBack;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.follow);

//    }

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.follow);
    }

    @Override
    protected void initView() {
        image_back.setOnClickListener(this);
        my_follow.setOnClickListener(this);
        follow_my.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.image_back://返回键监听
//                ActivityUtils.startHreatFragment();
//                break;
            case R.id.my_follow://我关注的监听
//                presenter = new RelationActivityPresenterImpl();
                my_follow.setTextColor(this.getResources().getColor(R.color.text_yellow_color));
//                my_follow.setBackground(this.getDrawable(R.mipmap.my_foll));
                break;

        }
    }

    @Override
    public void setFrientData(FrientBaen frientBaen) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }
//
//    @OnClick(R.id.image_back)
//    public void onClick() {
//        ActivityUtils.startHreatFragment();
//    }
}
