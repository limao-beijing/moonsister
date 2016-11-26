package com.moonsister.tcjy.main.widget;

import android.content.Intent;
import android.view.View;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.UserInfoManager;


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
        String authcode = UserInfoManager.getInstance().getAuthcode();
        if (StringUtis.isEmpty(authcode)) {
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
