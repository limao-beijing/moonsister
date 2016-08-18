package io.rong.imkit;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/8/8.
 */
public class IMManager {
    private volatile static IMManager instance;
    private boolean isRun;
    private IMManagerHandler handler;
    private String authcode;
    private String channel;

    public static IMManager getInstance() {
        if (instance == null) {
            synchronized (IMManager.class) {
                if (instance == null) {
                    instance = new IMManager();
                }

            }
        }
        return instance;
    }

    public static class IMManagerHandler extends Handler {
        private WeakReference<IMManager> weakReference;

        public IMManagerHandler(IMManager imManager) {
            weakReference = new WeakReference<IMManager>(imManager);
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakReference.get().isRun) {
                weakReference.get().tarkRun();
            }
        }
    }

    public void start(String authcode, String channel) {

        if (isRun)
            return;
        this.authcode = authcode;
        this.channel = channel;
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
                .subscribe(new Subscriber<MsgBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        RongLogUtils.e(IMManager.this, "error : " + e.getMessage());
                    }

                    @Override
                    public void onNext(MsgBean s) {
                        RongLogUtils.d(IMManager.this, "msg　：　" + s.toString());
                        handler.sendEmptyMessageDelayed(1, getRandomTime());
                    }
                });
    }

    private long getRandomTime() {
        long l = (long) ((Math.random() * 8 + 2) * 60 * 1000);
        RongLogUtils.d(this, "random : " + l);
        return l;

    }
}
