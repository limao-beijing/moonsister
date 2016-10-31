package com.moonsister.tcjy.manager;

import android.os.Handler;
import android.os.Message;

import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.utils.LogUtils;

import java.lang.ref.WeakReference;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/10/28.
 */

public class IMLoopManager {
    private volatile static IMLoopManager instance;
    private boolean isRun;
    private IMManagerHandler handler;
    private String authcode;
    private String channel;

    public static IMLoopManager getInstance() {
        if (instance == null) {
            synchronized (IMManager.class) {
                if (instance == null) {
                    instance = new IMLoopManager();
                }

            }
        }
        return instance;
    }

    public static class IMManagerHandler extends Handler {
        private WeakReference<IMLoopManager> weakReference;

        public IMManagerHandler(IMLoopManager imManager) {
            weakReference = new WeakReference<IMLoopManager>(imManager);
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakReference.get().isRun) {
                weakReference.get().tarkRun();
            }
        }
    }

    public void start(String authcode, String channel) {
        this.authcode = authcode;
        this.channel = channel;
        if (isRun)
            return;
        isRun = true;
        if (handler == null)
            handler = new IMManagerHandler(this);
        handler.sendEmptyMessageDelayed(1, getRandomTime());
    }

    public void stop() {
        isRun = false;
        if (handler != null)
            handler.removeMessages(1);
    }

    private void tarkRun() {

        RongServerAPI.getRongAPI().sendAppStartMsg(authcode, channel)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(IMLoopManager.this, "error : " + e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean s) {
                        LogUtils.d(IMLoopManager.this, "msg　：　" + s.toString());
                        handler.sendEmptyMessageDelayed(1, getRandomTime());
                    }
                });
    }

    private long getRandomTime() {
        long l = (long) ((Math.random() * 8 + 5) * 60 * 1000);
//        l = (long) 1000;
        LogUtils.d(this, "random : " + l);
        return l;

    }
}
