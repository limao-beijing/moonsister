package com.moonsister.tcjy.login.model;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.hickey.network.ServerApi;
import com.hickey.network.bean.BaseBean;
import com.hickey.tool.security.MD5Util;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.hickey.network.aliyun.AliyunManager;
import com.hickey.tool.ConfigUtils;
import com.hickey.network.aliyun.FilePathUtlis;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/6/15.
 */
public class RegiterDataFragmentModelImpl implements RegiterDataFragmentModel {


    @Override
    public void login(String face, String sex, String pwd, String authcode, onLoadDateSingleListener listener) {
        Observable<BaseBean> observable = ServerApi.getAppAPI().regiterLogin(face == null ? "" : face, sex, MD5Util.string2MD5(pwd), AppConstant.CHANNEL_ID, authcode);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }


    @Override
    public void upLoadIcon(String iconPath, onLoadDateSingleListener listener) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String host = AliyunManager.getInstance(ConfigUtils.getInstance().getApplicationContext()).upLoadFile(iconPath, FilePathUtlis.FileType.JPG);
                    subscriber.onNext(host);
                } catch (ClientException e) {
                    subscriber.onError(e);
                    e.printStackTrace();
                } catch (ServiceException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(ConfigUtils.getInstance().getResources().getString(R.string.upload_failure));

                    }

                    @Override
                    public void onNext(String host) {
                        listener.onSuccess(host, DataType.DATA_ONE);
                    }
                });

    }
}
