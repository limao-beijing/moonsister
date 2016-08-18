package com.moonsister.tcjy.utils;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/7/2.
 */
public class ObservableUtils {

    public static <T extends BaseBean> void parser(Observable<T> observable, Callback callback) {
        observable.
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("ObservableUtils", "onError : " + e.getMessage());
                        if (callback != null)
                            callback.onFailure(UIUtils.getStringRes(R.string.network_error));
                        LogUtils.e("ObservableUtils", e.getMessage());

                    }

                    @Override
                    public void onNext(T t) {
                        if (callback == null)
                            return;
                        if (t == null) {
                            callback.onFailure(UIUtils.getStringRes(R.string.request_failed));
                            return;
                        }
                        if (StringUtis.equals("1000", t.getCode())) {
                            callback.onFailure(UIUtils.getStringRes(R.string.code_timeout));
                            RxBus.getInstance().send(Events.EventEnum.LOGIN_CODE_TIMEOUT, null);
                            return;
                        }
                        if (!StringUtis.equals(AppConstant.code_request_success, t.getCode())) {
                            callback.onFailure(t.getMsg());
                            return;
                        }
                        callback.onSuccess(t);

                    }
                });


    }

    public interface Callback<T> {
        /**
         * 加载成功
         *
         * @param t
         */

        void onSuccess(T t);

        /**
         * 数据加载失败
         *
         * @param msg
         */
        void onFailure(String msg);
    }
}
