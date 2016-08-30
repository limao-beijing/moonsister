package com.moonsister.tcjy.main.widget;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/30.
 */
public class FillOutMessageActivity extends BaseActivity {
    @Bind(R.id.fillout_ok)//完成
    TextView fillout_ok;
    @Bind(R.id.fillout_go)//跳过
    TextView fillout_go;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_filloutmessage);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.fillout_ok,R.id.fillout_go})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fillout_ok:
                Intent intent=new Intent(FillOutMessageActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.fillout_go:
                Intent in=new Intent(FillOutMessageActivity.this,MainActivity.class);
                startActivity(in);
                break;
        }
    }
}
