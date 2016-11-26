package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.ImageView;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/9/7.
 */
public class TeQueActivity extends BaseActivity {
    @Bind(R.id.image_back)//返回键
    ImageView image_back;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.tequeactivity);
    }

    @Override
    protected void initView() {
    }

    @OnClick(R.id.image_back)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_back:
                this.finish();
                break;
        }
    }
}
