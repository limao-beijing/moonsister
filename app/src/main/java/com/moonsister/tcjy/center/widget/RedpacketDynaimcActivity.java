package com.moonsister.tcjy.center.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by jb on 2016/6/28.
 */
public class RedpacketDynaimcActivity extends BaseActivity {
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_red_packet_dynamic);
    }

    @Override
    protected void initView() {
        setRxBus();
    }

    @OnClick({R.id.iv_back, R.id.iv_add_pic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add_pic:
                ActivityUtils.startPhonePicActivity();
                break;
        }
    }

    private void setRxBus() {
        RxBus.with(this).setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.GET_PHOTO_LIST)
                .onNext(events ->
                        {

                            if (events != null) {
                                Object message = events.getMessage();
                                if (message != null && message instanceof ArrayList) {
                                    ArrayList pics = (ArrayList) message;
                                    LogUtils.e(DefaultDynamicSendActivity.class, "pics : " + pics.toString());
                                    ActivityUtils.startDefaultDynamicSendActivity(pics, EnumConstant.DynamicType.CHARGE_PIC);
                                    finish();

                                }
                            }
                        }
                ).create();
    }

}
