package com.moonsister.tcjy.main.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jb on 2016/7/12.
 */
public class DynamicAtionActivity extends Activity {
    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.tv_up)
    TextView tvUp;
    private String id;
    private boolean isMy;
    private int type;
    private String top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_action);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        id = intent.getStringExtra("id");
        type = intent.getIntExtra("type", -1);
        top = intent.getStringExtra("top");
        String id1 = UserInfoManager.getInstance().getMemoryPersonInfoDetail().getId();
        isMy = StringUtis.equals(uid, id1);
        if (isMy) {
            tvDelete.setText(UIUtils.getStringRes(R.string.delete) + UIUtils.getStringRes(R.string.dynamic));
            if (type == EnumConstant.DynamicType.CHARGE_PIC.getValue()||type == EnumConstant.DynamicType.CHARGE_VIDEO.getValue()||type == EnumConstant.DynamicType.CHARGE_VOICE.getValue()) {
                TextView up = (TextView) findViewById(R.id.tv_up);
                if (StringUtis.equals(top, "1")) {
                    up.setText(UIUtils.getStringRes(R.string.cancel) + UIUtils.getStringRes(R.string.up_dynamic));
                } else
                    up.setText(UIUtils.getStringRes(R.string.up_dynamic));
                up.setVisibility(View.VISIBLE);
            }
        } else {
            tvDelete.setText(UIUtils.getStringRes(R.string.report));

        }


    }

    @OnClick({R.id.tv_delete, R.id.tv_up, R.id.tv_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
                if (isMy) {
                    Events<String> events = new Events<>();
                    events.what = Events.EventEnum.DYNAMIC_DELETE_ACTION;
                    events.message = id;
                    RxBus.getInstance().send(events);
                } else {
                    UIUtils.showToast(this, UIUtils.getStringRes(R.string.success));
                }
                finish();
                break;
            case R.id.tv_up:
                if (isMy) {
                    Events<String> events = new Events<>();
                    if (StringUtis.equals(top, "1"))
                        events.what = Events.EventEnum.DYNAMIC_DEL_UP_ACTION;
                    else
                        events.what = Events.EventEnum.DYNAMIC_UP_ACTION;
                    events.message = id;
                    RxBus.getInstance().send(events);
                }
                finish();
                break;
            case R.id.tv_finish:
                finish();
                break;
        }
    }
}
