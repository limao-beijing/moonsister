package com.moonsister.tcjy.center.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.OnClick;

/**
 * Created by jb on 2016/6/23.
 */
public class DynamicSendActivity extends BaseActivity {


    @Override
    protected View setRootContentView() {
        View view = UIUtils.inflateLayout(R.layout.activity_dynamic_send);
        return view;
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.send_red_packet_list, R.id.send_video, R.id.send_dynamic, R.id.iv_action_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_red_packet_list:
                ActivityUtils.startRedpacketDynaimcActivity();
                finish();
                break;
            case R.id.send_video:
                ActivityUtils.startTakeVideoActivity();
                finish();
                break;
            case R.id.send_dynamic:
//                ActivityUtils.startDefaultDynamicSendActivity(null, DynamicType.FREE_PIC);
                ActivityUtils.startActivity(DynamicPublishActivity.class);
                finish();
                break;
            case R.id.iv_action_back:
                finish();
                break;

        }
    }
}
