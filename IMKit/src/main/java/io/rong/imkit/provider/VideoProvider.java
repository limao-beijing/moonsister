package io.rong.imkit.provider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import java.io.File;

import io.rong.imkit.R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.ImageMessage;

/**
 * Created by jb on 2016/8/20.
 */
public class VideoProvider extends  InputProvider.ExtendProvider {
    public VideoProvider(RongContext context) {
        super(context);
    }

    @Override
    public Drawable obtainPluginDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.rc_ic_camera);
    }

    @Override
    public CharSequence obtainPluginTitle(Context context) {
        return "vido";
    }

    @Override
    public void onPluginClick(View view) {

    }
    /**
     * 发送图片消息上传到服务器
     * @param firstImgPath  视频第一帧画面的本地 path
     * @param videoPath     视频存储的本地路径
     * @param mConversationType  会话类型
     * @param targetId  目标id
     */
//    public void sendVideoMessage(String firstImgPath, final String videoPath, Conversation.ConversationType mConversationType, String targetId) {
//        if (TextUtils.isEmpty(firstImgPath)) {
//            new RuntimeException("firstImgPath is null");
//            return;
//        }
//
//        File f = new File(firstImgPath);
//        if (!f.exists()) {
//            new RuntimeException("image file is null");
//            return;
//        }
//        if (TextUtils.isEmpty(videoPath)) {
//            new RuntimeException("videoPath is null");
//            return;
//        }
//        if (mConversationType == null) {
//            new RuntimeException("ConversationType is null");
//            return;
//        }
//        if (TextUtils.isEmpty(targetId)) {
//            new RuntimeException("targetId is null");
//            return;
//        }
//
//        ImageMessage imageMessage = ImageMessage.obtain(Uri.parse("file://" + firstImgPath), Uri.parse("file://" + firstImgPath));
//        io.rong.imlib.model.Message message = io.rong.imlib.model.Message.obtain(targetId, mConversationType, imageMessage);
//        RongIM.getInstance().getRongIMClient().sendImageMessage(message, null, null, new RongIMClient.SendImageMessageWithUploadListenerCallback() {
//            @Override
//            public void onAttached(final io.rong.imlib.model.Message message, final RongIMClient.uploadImageStatusListener watcher) {
//                message.setSentStatus(Message.SentStatus.SENDING);
//                RongIMClient.getInstance().setMessageSentStatus(message.getMessageId(), message.getSentStatus());
//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        ImageMessage img = (ImageMessage) message.getContent();
//                        img.setLocalUri(Uri.parse((videoPath)));
//
//                        io.rong.imlib.model.Message msg = io.rong.imlib.model.Message.obtain(message.getTargetId(), message.getConversationType(), img);
//                        RongIMClient.getInstance().uploadMedia(msg , RongIMClient.MediaType.VIDEO, new RongIMClient.UploadMediaCallback() {
//                            @Override
//                            public void onSuccess(Message message) {
////                                watcher.update(progress);
//                            }
//
//                            @Override
//                            public void onProgress(Message message, int i) {
//
//                            }
//
//                            @Override
//                            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
//
//                            }
//                        });
////                        RongIMClient.getInstance().uploadMedia(msg, new RongIMClient.UploadMediaCallback() {
////                            @Override
////                            public void onProgress(io.rong.imlib.model.Message message, int progress) {
////                                watcher.update(progress);
////                            }
////
////                            @Override
////                            public void onError(io.rong.imlib.model.Message message, RongIMClient.ErrorCode errorCode) {
////                                watcher.error();
////                            }
////
////                            @Override
////                            public void onSuccess(io.rong.imlib.model.Message message) {
////                                watcher.success();
////                            }
////                        });//
//                    }
//                };
//                new Handler().post(runnable);
//            }
//
//            @Override
//            public void onError(io.rong.imlib.model.Message message, RongIMClient.ErrorCode code) {
//
//            }
//
//            @Override
//            public void onSuccess(io.rong.imlib.model.Message message) {
//                message.setSentStatus(Message.SentStatus.SENT);
//                RongIMClient.getInstance().setMessageSentStatus(message.getMessageId(), message.getSentStatus());
//            }
//
//            @Override
//            public void onProgress(io.rong.imlib.model.Message message, int progress) {
//
//            }
//        });
//    }

}
