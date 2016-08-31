package com.moonsister.tcjy.main.widget;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;


/**
 * Creaort com.example.baidu.BaiduManager;ted by jb on 2016/8/30.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_spalsh);
    }

    @Override
    protected void initView() {
        ViewGroup viewById = (ViewGroup) findViewById(R.id.fl_content);
//        BaiduManager.getInstance(getApplicationContext()).adSplashBanner(this, viewById, MainActivity.class);
        UIUtils.sendDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
