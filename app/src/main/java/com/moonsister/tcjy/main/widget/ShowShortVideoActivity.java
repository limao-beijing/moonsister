package com.moonsister.tcjy.main.widget;

import java.io.File;


import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.VideoView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;
import com.umeng.analytics.MobclickAgent;

public class ShowShortVideoActivity extends BaseActivity implements
        OnTouchListener {
    private String TAG = "ShowShortVideoActivity";
    private VideoView vv;
    private String path;

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_show_short_video);
    }

    @Override
    protected void initView() {
        vv = (VideoView) findViewById(R.id.vv);
        Intent intent = getIntent();
        path = intent.getStringExtra("path");

        if (TextUtils.isEmpty(path)) {
            finish();
            return;
        }
//        if (!new File(path).exists()) {
//            Log.e(TAG, "********文件不存在*******");
//            finish();
//            return;
//        }
//        vv.setVideoURI(Uri.fromFile(new File(path)));
        try {
            Uri uri = Uri.parse(path);
            vv.setVideoURI(uri);
            vv.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (vv != null) {
                        finish();
                    }

                }
            });
            vv.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (vv != null) {
                        startPlay();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.showToast(this, "该视频不支持播放");
        }


    }

    protected void startPlay() {
        try {
            vv.start();
            if (mRootView != null)
                mRootView.setOnTouchListener(this);
        } catch (Exception e) {
            UIUtils.showToast(this, "该视频不支持播放");
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        finish();
        return true;
    }

}