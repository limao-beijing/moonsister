package com.moonsister.tcjy.engagement;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.CircularImageView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/9/21.
 */
public class EngagementTypeActivity extends BaseActivity {
    @Bind(R.id.civ_user_avater)
    CircularImageView mCivUserAvater;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagment);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String avater = intent.getStringExtra("avater");
        mTvUserName.setText(name);
        ImageServerApi.showURLSamllImage(mCivUserAvater, avater);

    }

    @OnClick({R.id.tv_fadai, R.id.tv_meal, R.id.tv_movie, R.id.tv_shop, R.id.tv_coffee, R.id.tv_travel, R.id.tv_more, R.id.iv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fadai:
                startActivity(new Intent(this,EngagemengOrderActivity.class));
                break;
            case R.id.tv_meal:
                break;
            case R.id.tv_movie:
                break;
            case R.id.tv_shop:
                break;
            case R.id.tv_coffee:
                break;
            case R.id.tv_travel:
                break;
            case R.id.tv_more:
                break;
            case R.id.iv_cancel:
                finish();
                break;
        }
    }
}
