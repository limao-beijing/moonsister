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
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.util.NetUtils;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by jb on 2016/10/18.
 */

public class IMManager {
    private String TAG = "IMManager";
    private volatile static IMManager instance;
    private Context mContext;
    private ConnectCallback mConnectionStatusListener;
    private onNotReadCallback mMsgNumber;


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
                LogUtils.d(TAG, "登录聊天服务器失败！");
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
                EMClient.getInstance().chatManager().deleteConversation(uid, true);
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
                setMessageListener();
            }

            @Override
            public void onDisconnected(int error) {
                UIUtils.onRunMainThred(new Runnable() {
                    @Override
                    public void run() {
                        if (error == EMError.USER_REMOVED) {
                            // 显示帐号已经被移除
                            LogUtils.d(TAG, " 显示帐号已经被移除！");
                        } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                            // 显示帐号在其他设备登录
                            LogUtils.d(TAG, "显示帐号在其他设备登录！");
                            if (mConnectionStatusListener != null) {
                                mConnectionStatusListener.offline();
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

    /**
     * 连接回调
     */
    public interface ConnectCallback {
        void onSuccess(String s);

        void offline();

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

    public void setMessageListener() {
        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {


                EaseAtMessageHelper.get().parseMessages(list);

                try {
                    HxUserDao dao = new HxUserDao();
                    for (EMMessage message : list) {
                        JSONObject userinfo = message.getJSONObjectAttribute("userinfo");
                        String name = userinfo.getString("nike");
                        String avater = userinfo.getString("avater");
                        EaseUser user = new EaseUser(message.getFrom());
                        user.setAvatar(avater);
                        user.setNick(name);
                        dao.saveUser(user);
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


}
