package com.moonsister.tcjy.main.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.CertificationStatusBean;
import com.hickey.network.bean.RongyunBean;
import com.hickey.network.bean.UserFriendListBean;
import com.hickey.network.bean.UserPermissionBean;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.hickey.tool.widget.UIUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/7/1.
 */
public class MainActivityModelImpl implements MainActivityModel {
    @Override
    public void getRongyunKey(onLoadDateSingleListener listener) {
        String authcode = UserInfoManager.getInstance().getAuthcode();
        ServerApi.getAppAPI().getRongyunKey(authcode, AppConstant.CHANNEL_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<RongyunBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(RongyunBean rongyunBean) {
                        if (rongyunBean != null) {
                            String code = rongyunBean.getCode();
                            if (StringUtis.equals(code, AppConstant.code_timeout)) {
                                listener.onFailure(UIUtils.getStringRes(R.string.code_timeout));
                                RxBus.getInstance().send(Events.EventEnum.LOGIN, null);
                            } else if (StringUtis.equals(code, AppConstant.code_request_success)) {
                                listener.onSuccess(rongyunBean, DataType.DATA_ZERO);
                            } else
                                listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                        }

                    }
                });
    }

    @Override
    public void getCertificationStatus(onLoadDateSingleListener listener) {
        ServerApi.getAppAPI().getCertificationStatus(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<CertificationStatusBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                    }

                    @Override
                    public void onNext(CertificationStatusBean defaultDataBean) {


                        listener.onSuccess(defaultDataBean, DataType.DATA_ONE);
                    }
                });

    }

    @Override
    public void getUserPermission(onLoadDateSingleListener listener) {
        Observable<UserPermissionBean> observable = ServerApi.getAppAPI().getUserPermission(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<UserPermissionBean>() {
            @Override
            public void onSuccess(UserPermissionBean bean) {
                listener.onSuccess(bean, DataType.DATA_TWO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void getUserFriendList(onLoadDateSingleListener listener) {
        Observable<UserFriendListBean> observable = ServerApi.getAppAPI().getUserFriendList(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<UserFriendListBean>() {
            @Override
            public void onSuccess(UserFriendListBean bean) {
                listener.onSuccess(bean, DataType.DATA_THREE);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });

    }
}
