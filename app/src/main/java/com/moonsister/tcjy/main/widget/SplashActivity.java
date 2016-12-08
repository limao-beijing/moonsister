package com.moonsister.tcjy.main.widget;

import android.content.Intent;
import android.view.View;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.file.PrefUtils;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;


/**
 * by jb on 2016/8/30.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_spalsh);
    }

    @Override
    protected void initView() {
//        String authcode = UserInfoManager.getInstance().getAuthcode();
        boolean open = PrefUtils.getBoolean(getApplicationContext(), "FIRST_OPEN", false);
//        PrefUtils.setBoolean(getApplicationContext(), "FIRST_OPEN", true);
        if (!open) {
//            startActivity(new Intent(this, ManorGrilActivity.class));
            startActivity(new Intent(this, WelcomeGuideActivity.class));
            finish();
        } else {
            enterMian();
        }

    }

    private void enterMian() {
//        ViewGroup viewById = (ViewGroup) findViewById(R.id.fl_content);
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
