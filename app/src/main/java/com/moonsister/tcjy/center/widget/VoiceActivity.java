package com.moonsister.tcjy.center.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.speak.PressToSpeakListenr;
import com.moonsister.tcjy.widget.speak.VoicePlay;

/**
 * Created by jb on 2016/8/10.
 */
public class VoiceActivity extends Activity {
    private TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_activity);
        View iv_voice = findViewById(R.id.iv_voice);
        tv_time = (TextView) findViewById(R.id.tv_time);
        iv_voice.setOnTouchListener(new PressToSpeakListenr(this, null) {
            @Override
            public void sendListener(String filePath, String fileName, String length, boolean isResend) {
                isStop = false;
                setData(filePath, fileName, length, isResend);

            }

            @Override
            public void start() {
                tv_time.setText("开始了");
                time = 1;
                isStop = true;
                UIUtils.sendDelayedOneMillis(new Runnable() {
                    @Override
                    public void run() {
                        startTime();
                    }
                });
            }
        });
    }

    private int time;
    private boolean isStop = false;

    private void startTime() {
        if (tv_time != null) {
            tv_time.setText(time + " s");
        }
        if (isStop)
            UIUtils.sendDelayedOneMillis(new Runnable() {
                @Override
                public void run() {
                    time = time + 1;
                    startTime();
                }
            });
    }

    private void setData(String filePath, String fileName, String length, boolean isResend) {
        Intent intent = new Intent();
        intent.putExtra("path", filePath);
        intent.putExtra("length", length);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
