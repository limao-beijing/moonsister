package com.moonsister.tcjy.center.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.OnClick;

/**
 * Created by jb on 2016/6/23.
 */
public class DynamicSendActivity extends BaseActivity {


    @Override
    protected View setRootContentView() {
        View view = UIUtils.inflateLayout(R.layout.activity_dynamic_publish);
        return view;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_cancel, R.id.iv_dynamic, R.id.iv_engagement})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                break;
            case R.id.iv_engagement:
                ActivityUtils.startEengegamentPublishActivity();
                break;

            case R.id.iv_dynamic:
                ActivityUtils.startDynamicPublishActivity();

                break;
        }
        finish();
    }
}
