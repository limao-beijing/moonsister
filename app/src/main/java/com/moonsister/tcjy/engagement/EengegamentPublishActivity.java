package com.moonsister.tcjy.engagement;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.OnClick;

/**
 * Created by jb on 2016/9/22.
 */
public class EengegamentPublishActivity extends BaseActivity {
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagment_publish);
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.tv_fadai, R.id.tv_meal, R.id.tv_movie, R.id.tv_shop, R.id.tv_coffee, R.id.tv_travel, R.id.tv_more, R.id.iv_cancel})
    public void onClick(View view) {
        if (view.getId() == R.id.iv_cancel) {
            finish();
            return;
        }
        switch (view.getId()) {
            case R.id.tv_fadai:
                ActivityUtils.startActivity(EengegamentRecommendActivity.class);
                break;
            case R.id.tv_meal:
                break;
            case R.id.tv_movie:
                break;
            case R.id.tv_shop:
                break;
            case R.id.tv_coffee:
                break;
            case R.id.tv_travel:
                break;
            case R.id.tv_more:
                break;
        }
    }
}
