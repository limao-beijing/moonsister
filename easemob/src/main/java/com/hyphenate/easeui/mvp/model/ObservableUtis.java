package com.hyphenate.easeui.mvp.model;

import com.hickey.network.LogUtils;
import com.hickey.tool.base.BaseHttpFunc;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.base.BaseResponse;
import com.hickey.tool.base.HttpServiceException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/11/25.
 */
public class ObservableUtis {
    public static <T> void $(Observable<BaseResponse<T>> observable, final BaseIModel.DataType dataType, final BaseIModel.onLoadDateSingleListener listenter) {
        observable.map(new BaseHttpFunc<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpServiceException) {
                            listenter.onFailure(((HttpServiceException) e).getMessage());
                        } else {
                            listenter.onFailure("网络异常，请稍后再试");
                            LogUtils.e("http : " + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(T t) {
                        if (t == null) {
                            listenter.onFailure("没有数据");
                        } else
                            listenter.onSuccess(t, dataType);

                    }
                });

    }

}
