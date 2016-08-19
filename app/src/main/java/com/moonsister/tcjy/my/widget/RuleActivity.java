package com.moonsister.tcjy.my.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;

public class RuleActivity extends BaseActivity {


    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.talk);
    }

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_rule);
    }

    @Override
    protected void initView() {

    }
}
