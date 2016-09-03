package com.moonsister.tcjy.my.model;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BackTermsBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.manager.aliyun.AliyunManager;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.FilePathUtlis;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by x on 2016/9/3.
 */
public class RenZhengActivityModelImpl implements RenZhengActivityModel  {
    @Override
    public void loadData(onLoadDateSingleListener<BackTermsBean> listener) {
        Observable<BackTermsBean> observable = ServerApi.getAppAPI().backTermsBean( UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BackTermsBean>() {
            @Override
            public void onSuccess(BackTermsBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

//    @Override
//    public void upLoadIcon(String iconPath, onLoadDateSingleListener listener) {
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                try {
//                    String host = AliyunManager.getInstance().upLoadFile(iconPath, FilePathUtlis.FileType.JPG);
//                    subscriber.onNext(host);
//                } catch (ClientException e) {
//                    subscriber.onError(e);
//                    e.printStackTrace();
//                } catch (ServiceException e) {
//                    e.printStackTrace();
//                    subscriber.onError(e);
//                }
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        listener.onFailure(ConfigUtils.getInstance().getResources().getString(R.string.upload_failure));
//
//                    }
//
//                    @Override
//                    public void onNext(String host) {
//                        listener.onSuccess(host, DataType.DATA_ONE);
//                    }
//                });
//    }
}
