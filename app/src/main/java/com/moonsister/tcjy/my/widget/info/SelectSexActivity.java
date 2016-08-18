package com.moonsister.tcjy.my.widget.info;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/30.
 */
public class SelectSexActivity extends Activity {
    @Bind(R.id.tv_girls)
    TextView tvGirls;
    @Bind(R.id.tv_boy)
    TextView tvBoy;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sex);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_girls, R.id.tv_boy, R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_girls:
                sendRx(tvGirls.getText().toString().trim());
                break;
            case R.id.tv_boy:
                sendRx(tvBoy.getText().toString().trim());
                break;
            case R.id.tv_cancel:
                finish();
                break;
        }
    }

    private void sendRx(String sex) {
        Events<String> events = new Events<String>();
        events.what = Events.EventEnum.SELECT_PLAND_DATA;
        events.message = sex;
        RxBus.getInstance().send(events);
        finish();
    }


}
