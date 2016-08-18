package io.rong.imkit;


import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.File;
import java.net.URLEncoder;

import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import io.rong.message.RichContentMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/7/29.
 */
public class SendMsgForServiceHelper {
    public enum ChatType {
        TXT_MSG("RC:TxtMsg"), IMG_MSG("RC:ImgMsg"), VC_MSG("RC:VcMsg"), IMG_TEXTMSG("RC:ImgTextMsg"), LBS_MSG("RC:LBSMsg");
        private final String type;

        private ChatType(String type) {
            this.type = type;
        }

        public String getValue() {
            return type;
        }

    }

    private static void sendMsg(ChatType chatType, String touid, String content, String authcode) {
//        String msgContent = new Gson().toJson(context);
//        if (TextUtils.isEmpty(msgContent))
//            return;
        Observable<MsgBean> observable = RongServerAPI.getRongAPI().send(chatType.getValue(), touid, content, authcode);
        observable.observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MsgBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        RongLogUtils.d(SendMsgForServiceHelper.class, "Throwable : " + e.getMessage());
                    }

                    @Override
                    public void onNext(MsgBean msgBean) {
                        RongLogUtils.d(SendMsgForServiceHelper.class, "msgBean" + msgBean.toString());
                    }
                });
    }

    public void send(Message message, String authcode) {
        MessageContent messageContent = message.getContent();
        if (messageContent instanceof TextMessage) {// 文本消息
            TextMessage textMessage = (TextMessage) messageContent;
            RongLogUtils.e(this, "onSent-TextMessage:" + textMessage.getContent());
            sendMsg(ChatType.TXT_MSG, message.getTargetId(), textMessage.getContent(), authcode);
        } else if (messageContent instanceof ImageMessage) {// 图片消息
            ImageMessage imageMessage = (ImageMessage) messageContent;
            String path = imageMessage.getLocalUri().getPath();
            File file = new File(path);
            if (!file.exists()) {
                return;
            }
            String imgString = null;
            if (path.contains(".jpg")) {
                imgString = FileUtils.Bitmap2StrByBase64(BitmapFactory.decodeFile(path),
                        "data:image/jpg;base64,");
            } else if (path.contains(".png")) {
                imgString = FileUtils.Bitmap2StrByBase64(BitmapFactory.decodeFile(path),
                        "data:image/png;base64,");
            }
            if (TextUtils.isEmpty(imgString))
                return;
            imgString = URLEncoder.encode(imgString);
            sendMsg(ChatType.IMG_MSG, message.getTargetId(), imgString, authcode);
            RongLogUtils.e(this, "onSent-ImageMessage:" + imageMessage.getLocalUri().getPath());
        } else if (messageContent instanceof VoiceMessage) {// 语音消息
            VoiceMessage voiceMessage = (VoiceMessage) messageContent;
            RongLogUtils.e(this, "onSent-voiceMessage:"
                    + voiceMessage.getUri().toString());
            String path = voiceMessage.getUri().getPath();
            File fileSound = new File(path);
            if (!fileSound.exists()) {
                return;
            }
            String voiceString = FileUtils.getString(path, "data:audio/amr;base64,");
            voiceString = URLEncoder.encode(voiceString);
            sendMsg(ChatType.VC_MSG, message.getTargetId(), voiceString, authcode);
        } else if (messageContent instanceof RichContentMessage) {// 图文消息
            RichContentMessage richContentMessage = (RichContentMessage) messageContent;
            RongLogUtils.e(this,
                    "onSent-RichContentMessage:"
                            + richContentMessage.getContent());
        } else {
            RongLogUtils.e(this, "onSent-其他消息，自己来判断处理");
        }
    }

    private MsgContent.UserBean gerUser(String mTargetId) {
        UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(mTargetId);
        if (userInfo == null)
            return null;

        MsgContent.UserBean userBean = new MsgContent.UserBean();
        userBean.setIcon(userInfo.getPortraitUri().getPath());
        userBean.setId(mTargetId);
        userBean.setName(userInfo.getName());
        return userBean;
    }


}



