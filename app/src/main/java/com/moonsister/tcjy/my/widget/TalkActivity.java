package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.ImageView;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;

import butterknife.Bind;

/**
 * Created by x on 2016/9/7.
 */
public class TalkActivity extends BaseActivity {
    @Bind(R.id.image_back)//返回
    ImageView image_back;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.talkactivity);
    }

    @Override
    protected void initView() {
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TalkActivity.this.finish();
            }
        });
    }
}
