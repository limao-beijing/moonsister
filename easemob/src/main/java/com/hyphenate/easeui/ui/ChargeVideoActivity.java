package com.hyphenate.easeui.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;

import com.easemob.easeui.R;
import com.hickey.network.LogUtils;
import com.hickey.tool.base.BaseDialogFragment;
import com.hickey.tool.constant.EnumConstant;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.CustomConstant;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Nathen on 16/7/31.
 */
public class ChargeVideoActivity extends AppCompatActivity {
    private String path;
    private String money;
    private String lid;
    private String mActhcode;
    private boolean look;
    private EMMessage message;
    private long playTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        path = getIntent().getStringExtra("path");
        message = getIntent().getParcelableExtra(CustomConstant.ESSAGE_ATTRIBUTE_EMMESSAGE);
        mActhcode = getIntent().getStringExtra(CustomConstant.ESSAGE_ATTRIBUTE_ACTHCODE);
        if (TextUtils.isEmpty(path)) {
            finish();
            return;
        }
        setContentView(R.layout.activity_directly_play);
        try {
            look = message.getBooleanAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LOOK);
            lid = message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LID);
            money = message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_MONEY);
            playTime = message.getLongAttribute(CustomConstant.ESSAGE_ATTRIBUTE_PLAY_TIME, 0);
            if (playTime > 2) {
                playTime = playTime - 1;
            }
        } catch (Exception e) {

        }

        final JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard);
        jcVideoPlayerStandard.setUp(path, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, "");
        jcVideoPlayerStandard.onClick(jcVideoPlayerStandard.startButton);
        if (!look && message.direct() == EMMessage.Direct.RECEIVE) {
            jcVideoPlayerStandard.setOnProgressChangeListenter(new JCVideoPlayer.OnProgressChangeListenter() {
                @Override
                public void onChange(final JCVideoPlayer player, int progress, int secProgress, int currentTime, int totalTime) {
                    if (!look && playTime < currentTime) {
                        player.onClick(jcVideoPlayerStandard.startButton);
                        ChargeMessageDialog dialog = new ChargeMessageDialog();
                        Bundle bundle = new Bundle();
                        bundle.putString(CustomConstant.ESSAGE_ATTRIBUTE_ACTHCODE, mActhcode);
                        bundle.putParcelable(CustomConstant.ESSAGE_ATTRIBUTE_EMMESSAGE, message);
                        dialog.setArguments(bundle);
                        dialog.setOnCallBack(new BaseDialogFragment.OnCallBack() {
                            @Override
                            public void onStatus(BaseDialogFragment dialogFragment, EnumConstant.DialogCallBack statusCode) {
                                if (statusCode == EnumConstant.DialogCallBack.CONFIRM) {
                                    dialogFragment.dismissDialogFragment();
                                    look = true;
                                    jcVideoPlayerStandard.onClick(jcVideoPlayerStandard.startButton);
                                } else {
                                    ChargeVideoActivity.this.finish();
                                }
                            }
                        });
                        dialog.showDialogFragment(getSupportFragmentManager());
                    }
                    LogUtils.e(ChargeVideoActivity.this, "----" + currentTime);
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
