/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.moonsister.tcjy.widget.speak;

import java.io.File;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.moonsister.tcjy.utils.StringUtis;


public class VoicePlay {
    private static final String TAG = "VoicePlayClickListener";

    ImageView voiceIconView;

    private AnimationDrawable voiceAnimation = null;
    MediaPlayer mediaPlayer = null;
    ImageView iv_read_status;
    private BaseAdapter adapter;

    public static boolean isPlaying = false;
    public static VoicePlay currentPlayListener = null;


    public void stopPlayVoice() {
        if (isPlaying && voiceAnimation != null) {
            voiceAnimation.stop();
        }
        if (isPlaying && mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        isPlaying = false;
    }

    public void playVoice(Context context, String filePath) {
        if (StringUtis.isEmpty(filePath))
            return;
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        mediaPlayer = new MediaPlayer();
//		if (SDKHelper.getInstance().getModel().getSettingMsgSpeaker()) {
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
//		} else {
//			audioManager.setSpeakerphoneOn(false);// 关闭扬声器
//			// 把声音设定成Earpiece（听筒）出来，设定为正在通话中
//			audioManager.setMode(AudioManager.MODE_IN_CALL);
//			mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
//		}
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mediaPlayer.release();
                    mediaPlayer = null;
                    stopPlayVoice(); // stop animation
                }

            });
            isPlaying = true;
            currentPlayListener = this;
            mediaPlayer.start();
        } catch (Exception e) {
        }
    }

}