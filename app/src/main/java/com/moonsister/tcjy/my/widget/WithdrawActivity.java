package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/26.
 */
public class WithdrawActivity extends BaseActivity {
    @Bind(R.id.linear)//支付宝
    LinearLayout linear;
    @Bind(R.id.linear1)//微信
    LinearLayout linear1;
    @Bind(R.id.add_alipay)//添加支付宝账号
    TextView add_alipay;
    @Bind(R.id.add_weixin)//添加微信账号
    TextView add_weixin;
    @Bind(R.id.withdraw_textview)//确认按钮
    TextView withdraw_textview;
    @Bind(R.id.image_with)//支付宝√
    ImageView image_with;
    @Bind(R.id.image_withdraw)//微信√
    ImageView image_withdraw;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_withdraw);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.linear,R.id.linear1,R.id.add_alipay,R.id.add_weixin,R.id.withdraw_textview})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linear:
                image_with.setVisibility(View.VISIBLE);
                image_withdraw.setVisibility(View.INVISIBLE);
                break;
            case R.id.linear1:
                image_with.setVisibility(View.INVISIBLE);
                image_withdraw.setVisibility(View.VISIBLE);
                break;
            case R.id.add_alipay:

                break;
            case R.id.add_weixin:

                break;
            case R.id.withdraw_textview:
                withdraw_textview.setBackgroundResource(R.mipmap.recharge);
                ActivityUtils.startAddActivity();
                break;
        }
    }
}
