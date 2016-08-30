package com.moonsister.tcjy.main.widget;

import android.content.Intent;
import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/8/30.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_spalsh);
    }

    @Override
    protected void initView() {
        UIUtils.sendDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
