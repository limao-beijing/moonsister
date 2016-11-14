package com.moonsister.tcjy.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.Constant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.db.HxUserDao;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.util.NetUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tool.lang.StringUtis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/10/18.
 */

public class IMManager {
    private String TAG = "IMManager";
    private volatile static IMManager instance;
    private Context mContext;
    private ConnectCallback mConnectionStatusListener;
    private onNotReadCallback mMsgNumber;
    private int logServiceCount = 0;
    protected final static String[] msgs = {"发来一条消息", "发来一张图片", "发来一段语音", "发来位置信息", "发来一个视频", "发来一个文件",
            "%1个联系人发来%2条消息"
    };

    public static IMManager getInstance() {
        if (instance == null) {
            synchronized (UserInfoManager.class) {
                if (instance == null) {
                    instance = new IMManager();
                }

            }
        }
        return instance;
    }

    public void initEaseUI(Context context) {
        this.mContext = context;
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(mContext, options);

        setUserInfo();
        setConnectionListener();
        setMessageListener();
    }

    /**
     * 是否连接
     *
     * @return
     */
    public boolean isConnected() {
        return EMClient.getInstance().isConnected();
    }

    /**
     * 登录环信
     *
     * @param userName
     * @param password
     */
    public void loginIMService(String userName, String password, ConnectCallback callback) {
        if (logServiceCount > 20) {
            return;
        }
        logServiceCount++;
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                LogUtils.d(TAG, "登录聊天服务器成功！");
                if (callback != null) {
                    callback.onSuccess("登录聊天服务器成功");
                }
                PersonInfoDetail detail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
                HxUserDao dao = new HxUserDao();
                EaseUser user = new EaseUser(detail.getId());
                user.setAvatar(detail.getFace());
                user.setNick(detail.getNickname());
                dao.saveUser(user);

            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                LogUtils.e(TAG, "登录聊天服务器失败！:" + message);
                if (callback != null) {
                    callback.onTokenIncorrect();
                }
            }
        });
    }

    public void setUserInfo() {
        EaseUI.getInstance().setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String username) {
                HxUserDao dao = new HxUserDao();
                EaseUser user = dao.getUser(username);
                if (user == null) {
                    user = new EaseUser(username);
                }
                return user;
            }
        });
        EaseUI.getInstance().getNotifier().setNotificationInfoProvider(new EaseNotifier.EaseNotificationInfoProvider() {
            @Override
            public String getDisplayedText(EMMessage message) {
                EaseUser user = EaseUI.getInstance().getUserProfileProvider().getUser(message.getFrom());
                if (user == null)
                    return "";
                String username = user.getNick();
                String notifyText = username + " ";
                switch (message.getType()) {
                    case TXT:
                        notifyText += msgs[0];
                        break;
                    case IMAGE:
                        notifyText += msgs[1];
                        break;
                    case VOICE:

                        notifyText += msgs[2];
                        break;
                    case LOCATION:
                        notifyText += msgs[3];
                        break;
                    case VIDEO:
                        notifyText += msgs[4];
                        break;
                    case FILE:
                        notifyText += msgs[5];
                        break;


                }
                return notifyText;

            }

            @Override
            public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
                return null;
            }

            @Override
            public String getTitle(EMMessage message) {
                return null;
            }

            @Override
            public int getSmallIcon(EMMessage message) {
                return R.mipmap.ic_samll_launcher;
            }

            @Override
            public Intent getLaunchIntent(EMMessage message) {
                return mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
            }
        });
    }

    /**
     * 更新
     *
     * @param uid
     */
    public void upUserInfo(String uid) {
        EaseUserUtils.upUserInfo(uid);
    }


    /**
     * 退出登录
     *
     * @param uid
     */
    public void logoutIMService(String uid) {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                LogUtils.d(TAG, "退出聊天服务器成功！");
                //删除和某个user会话，如果需要保留聊天记录，传false
                cleanAllChatMsg();
                logServiceCount = 0;
            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(int code, String message) {
                LogUtils.d(TAG, "退出聊天服务器失败！");

            }
        });


    }

    /**
     * 删除某一条消息
     *
     * @param uid
     * @param msgId
     */
    public void removeMessage(String uid, String msgId) {
        //删除当前会话的某条聊天记录
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(uid);
        conversation.removeMessage(msgId);
    }

    /**
     * 状态连接监听
     *
     * @param
     */
    public void setConnectionListener() {
        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {

                LogUtils.d(TAG, " 连接成功！");

            }

            @Override
            public void onDisconnected(int error) {
                UIUtils.onRunMainThred(new Runnable() {
                    @Override
                    public void run() {
                        if (error == EMError.USER_REMOVED) {
                            // 显示帐号已经被移除
                            LogUtils.d(TAG, " 显示帐号已经被移除！");
                            if (mConnectionStatusListener != null) {
                                mConnectionStatusListener.offline("显示帐号已经被移除");
                            }
                        } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                            // 显示帐号在其他设备登录
                            LogUtils.d(TAG, "显示帐号在其他设备登录！");
                            if (mConnectionStatusListener != null) {
                                mConnectionStatusListener.offline("显示帐号在其他设备登录");
                            }
                        } else {
                            if (NetUtils.hasNetwork(mContext)) {
                                //连接不到聊天服务器
                                LogUtils.d(TAG, "连接不到聊天服务器！");
                            } else {
                                //当前网络不可用，请检查网络设置
                                LogUtils.d(TAG, "当前网络不可用，请检查网络设置！");
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * 连接监听
     *
     * @param connectionStatusListener
     */
    public void setConnectionStatusListener(ConnectCallback connectionStatusListener) {
        mConnectionStatusListener = connectionStatusListener;
    }

    public void setMsgNumber(onNotReadCallback msgNumber) {
        mMsgNumber = msgNumber;
    }

    public void cleanAllChatMsg() {
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        for (String s : conversations.keySet()) {
            EMClient.getInstance().chatManager().deleteConversation(s, true);
        }
    }

    /**
     * 连接回调
     */
    public interface ConnectCallback {
        void onSuccess(String s);

        void offline(String msg);

        void onTokenIncorrect();
    }

    /**
     * 未读数回调
     */
    public interface onNotReadCallback {
        void onSuccess(int number);
    }

    /**
     * 下线
     */
    public void offline(String uid) {
        logoutIMService(uid);

    }

    public void uploadUnreadMsgCountTotal() {
        if (mMsgNumber != null) {
            UIUtils.onRunMainThred(new Runnable() {
                @Override
                public void run() {
                    mMsgNumber.onSuccess(getUnreadMsgCountTotal());
                }
            });

        }
    }

    /**
     * 保存用户信息
     *
     * @param uid
     * @param nike
     * @param avater
     */
    public void saveUserInfo(String uid, String nike, String avater) {
        EaseUser eu = EaseUI.getInstance().getUserProfileProvider().getUser(uid);
        if (eu != null && StringUtis.equals(eu.getAvatar(), avater) && StringUtis.equals(eu.getNick(), nike)) {
            return;
        }
        HxUserDao dao = new HxUserDao();
        EaseUser user = new EaseUser(uid);
        user.setAvatar(avater);
        user.setNick(nike);
        dao.saveUser(user);
        IMManager.getInstance().upUserInfo(uid);
    }


    public void setMessageListener() {

        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {


                EaseAtMessageHelper.get().parseMessages(list);

                try {
                    for (EMMessage message : list) {
                        JSONObject userinfo = message.getJSONObjectAttribute("userinfo");
                        String name = userinfo.getString("nike");
                        String avater = userinfo.getString("avater");
                        String from = message.getFrom();
                        saveUserInfo(from, name, avater);
                        EaseUI instance = EaseUI.getInstance();
                        instance.getNotifier().onNewMsg(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                uploadUnreadMsgCountTotal();
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {
                if (list == null)
                    return;
                try {
                    Observable.create(new Observable.OnSubscribe<EMMessage>() {
                        @Override
                        public void call(Subscriber<? super EMMessage> subscriber) {
                            for (EMMessage message : list) {
                                try {
                                    JSONObject cmdmsg1 = message.getJSONObjectAttribute("cmdmsg");
                                    String uid = cmdmsg1.getString("b_uid");
                                    String msg = cmdmsg1.getString("msg");
                                    String nickname = cmdmsg1.getString("b_nickname");
                                    String face = cmdmsg1.getString("b_face");

                                    saveUserInfo(uid, nickname, face);

                                    String type = cmdmsg1.getString("type");
                                    EMMessage sendMessage = createTxtSendMessage(msg, uid);
                                    //send message
                                    JSONObject object = new JSONObject();
                                    EaseUser user = EaseUI.getInstance().getUserProfileProvider().getUser(message.getTo());
                                    try {
                                        object.put("nike", user.getNick());
                                        object.put("avater", user.getAvatar());
                                        message.setAttribute("userinfo", object);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    subscriber.onNext(sendMessage);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    subscriber.onError(e);
                                }
                            }
                            subscriber.onCompleted();
                        }
                    }).subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe(new Subscriber<EMMessage>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(EMMessage s) {
                                    sendMessage(s);
                                    RxBus.getInstance().send(Events.EventEnum.EM_SEND_CMD, null);
                                }
                            });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageChanged(EMMessage message, Object o) {

            }
        });
    }

    private void registerBroadcastReceiver() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(mContext);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(Constant.ACTION_GROUP_CHANAGED);
        intentFilter.addAction("refresh_group_money_action");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if (mMsgNumber != null) {
                    mMsgNumber.onSuccess(getUnreadMsgCountTotal());
                }
                //end of red packet code
            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * get unread message count
     *
     * @return
     */
    public int getUnreadMsgCountTotal() {
        int unreadMsgCountTotal = 0;
        int chatroomUnreadMsgCount = 0;
        unreadMsgCountTotal = EMClient.getInstance().chatManager().getUnreadMsgsCount();
        for (EMConversation conversation : EMClient.getInstance().chatManager().getAllConversations().values()) {
            if (conversation.getType() == EMConversation.EMConversationType.ChatRoom)
                chatroomUnreadMsgCount = chatroomUnreadMsgCount + conversation.getUnreadMsgCount();
        }
        return unreadMsgCountTotal - chatroomUnreadMsgCount;
    }

    //send message
    public EMMessage createTxtSendMessage(String content, String toUid) {
//        if (EaseAtMessageHelper.get().containsAtUsername(content)) {
//            sendAtMessage(content);
//        } else {
        EMMessage message = EMMessage.createTxtSendMessage(content, toUid);
        return message;
//        }
    }

    private void sendMessage(EMMessage message) {
        if (message == null)
            return;
        EMClient.getInstance().chatManager().sendMessage(message);
        //refresh ui
    }

}
