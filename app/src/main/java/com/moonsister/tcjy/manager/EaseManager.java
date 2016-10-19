package com.moonsister.tcjy.manager;

import android.content.Context;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.util.NetUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/10/18.
 */

public class EaseManager {
    private String TAG = "EaseManager";
    private volatile static EaseManager instance;
    private Context mContext;


    public static EaseManager getInstance() {
        if (instance == null) {
            synchronized (UserInfoManager.class) {
                if (instance == null) {
                    instance = new EaseManager();
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
    }

    /**
     * 登录环信
     *
     * @param userName
     * @param password
     */
    public void loginHx(String userName, String password) {
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                LogUtils.d(TAG, "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                LogUtils.d(TAG, "登录聊天服务器失败！");
            }
        });
    }

    /**
     * 退出登录
     */
    public void logoutHx() {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                LogUtils.d(TAG, "退出聊天服务器成功！");

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
                        } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                            // 显示帐号在其他设备登录
                            LogUtils.d(TAG, "显示帐号在其他设备登录！");
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
}
