package com.moonsister.tcjy.engagement.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.OnClick;

/**
 * Created by jb on 2016/9/22.
 */
public class EngegamentPublishActivity extends BaseActivity {
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagment_publish);
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.tv_other, R.id.tv_fadai, R.id.tv_meal, R.id.tv_movie, R.id.tv_shop, R.id.tv_coffee, R.id.tv_travel, R.id.tv_more, R.id.iv_cancel})
    public void onClick(View view) {
        if (view.getId() == R.id.iv_cancel) {
            finish();
            return;
        }
        EnumConstant.EngegamentType type = null;
        switch (view.getId()) {
            case R.id.tv_fadai:
                type = EnumConstant.EngegamentType.fadai;
                break;
            case R.id.tv_meal:
                type = EnumConstant.EngegamentType.meal;
                break;
            case R.id.tv_movie:
                type = EnumConstant.EngegamentType.movie;
                break;
            case R.id.tv_shop:
                type = EnumConstant.EngegamentType.shop;
                break;
            case R.id.tv_coffee:
                type = EnumConstant.EngegamentType.coffee;
                break;
            case R.id.tv_travel:
                type = EnumConstant.EngegamentType.travel;
                break;
            case R.id.tv_other:
                type = EnumConstant.EngegamentType.other;
                break;
            case R.id.tv_more:
                type = EnumConstant.EngegamentType.All;
                break;

        }
        ActivityUtils.startEengegamentRecommendActivity(type);
        finish();
    }
}
