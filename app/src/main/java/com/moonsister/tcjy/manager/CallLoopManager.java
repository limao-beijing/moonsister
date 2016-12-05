package com.moonsister.tcjy.manager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.ModuleServerApi;
import com.hickey.network.bean.resposen.CallMessageBean;
import com.hickey.tool.base.BaseResponse;
import com.hickey.tool.base.HttpServiceException;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.viewholder.CallLoopViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/10/28.
 */

public class CallLoopManager {
    private volatile static CallLoopManager instance;
    private boolean isRun;
    private CallLoopManagerHandler handler;
    private String authcode;
    private String channel;
    private Activity mActivity;
    private ViewGroup mViewGroup;
    private ArrayList<CallMessageBean> datas = new ArrayList<CallMessageBean>();
    public static final int HANDLER_TYPE_LOOP = 1;
    public static final int HANDLER_TYPE_GONE_VIEW = 2;
    public static final int HANDLER_TYPE_DELAYED_SHOW_VIEW = 3;

    public static CallLoopManager getInstance() {
        if (instance == null) {
            synchronized (IMManager.class) {
                if (instance == null) {
                    instance = new CallLoopManager();
                }

            }
        }
        return instance;
    }

    public static class CallLoopManagerHandler extends Handler {
        private WeakReference<CallLoopManager> weakReference;

        public CallLoopManagerHandler(CallLoopManager imManager) {
            weakReference = new WeakReference<CallLoopManager>(imManager);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_TYPE_GONE_VIEW:
                    ViewGroup group = weakReference.get().mViewGroup;
                    if (group != null) {
                        removeMessages(HANDLER_TYPE_GONE_VIEW);
                        group.setVisibility(View.GONE);
                    }
                    sendEmptyMessage(HANDLER_TYPE_DELAYED_SHOW_VIEW);
                    break;
                case HANDLER_TYPE_LOOP:
                    if (weakReference.get().isRun) {
                        removeMessages(HANDLER_TYPE_LOOP);
                        weakReference.get().showView();
                    }
                    break;
                case HANDLER_TYPE_DELAYED_SHOW_VIEW:
                    removeMessages(HANDLER_TYPE_DELAYED_SHOW_VIEW);
                    sendEmptyMessageDelayed(HANDLER_TYPE_LOOP, weakReference.get().getRandomTime());
                    break;
            }

        }
    }

    public void start(@NonNull Activity activity, @NonNull ViewGroup viewGroup, @NonNull String authcode, @NonNull String channel) {
        this.mActivity = activity;
        this.mViewGroup = viewGroup;
        this.authcode = authcode;
        this.channel = channel;
        if (isRun)
            return;
        isRun = true;
        if (handler == null)
            handler = new CallLoopManagerHandler(this);
        handler.sendEmptyMessageDelayed(HANDLER_TYPE_LOOP, 5000);
    }

    public void stop() {
        isRun = false;
        if (handler != null)
            handler.removeMessages(HANDLER_TYPE_LOOP);
    }

    private void tarkRun() {
        JSONObject jsonObject = new JSONObject();
        try {
            String adress = GaodeManager.getInstance().getStringAdress();
            JSONObject object = new JSONObject(adress);

            jsonObject.put("province", object.getString("province"));
            jsonObject.put("city", object.getString("city"));
            jsonObject.put("district", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Observable<BaseResponse<List<CallMessageBean>>> observable = ModuleServerApi.getAppAPI().getCallMessagePush(authcode, jsonObject.toString(), AppConstant.CHANNEL_ID);
        observable.map(new Func1<BaseResponse<List<CallMessageBean>>, List<CallMessageBean>>() {
            @Override
            public List<CallMessageBean> call(BaseResponse<List<CallMessageBean>> response) {
                int code = response.getCode();
                if (code != 1) {
                    throw new HttpServiceException(code, response.getMsg());
                }
                return response.getData();
            }

//            @Override
//            public List<CallMessageBean> call(BaseResponse<List<CallMessageBean>> response) {
//
//            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CallMessageBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<CallMessageBean> bean) {
                        if (bean == null)
                            return;
                        datas.addAll(bean);
                        if (datas.size() > 0)
                            showView();
                    }


                });

    }

    private CallLoopViewHolder holder;

    private void showView() {
        if (holder == null) {
            holder = new CallLoopViewHolder();
            mViewGroup.addView(holder.getContentView());
            holder.setHandler(handler);
            holder.setAuthcode(authcode);
        }
        if (datas.size() > 0) {
            holder.refreshView(datas.get(0));
            mViewGroup.setVisibility(View.VISIBLE);
            datas.remove(0);
        } else {
            tarkRun();
        }
    }

    private long getRandomTime() {
        long l = (long) ((Math.random() * 4 + 2) * 60 * 1000);
//        long l = 5000;
        LogUtils.e(this, "random : " + l);
        return l;

    }
}
