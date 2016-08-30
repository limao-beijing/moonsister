package com.moonsister.tcjy.login.widget;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.main.widget.FillOutMessageActivity;
import com.moonsister.tcjy.main.widget.MainActivity;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/30.
 */
public class RegActivity extends BaseActivity{
    @Bind(R.id.tv_submit)
    TextView tv_submit;
    @Bind(R.id.let_go)
    TextView let_go;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_reg);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.tv_submit,R.id.let_go})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_submit:
                Intent intent=new Intent(RegActivity.this, FillOutMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.let_go:
                Intent in=new Intent(RegActivity.this, MainActivity.class);
                startActivity(in);
                break;
        }
    }
}
