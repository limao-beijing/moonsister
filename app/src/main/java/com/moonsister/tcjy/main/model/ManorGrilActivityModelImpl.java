package com.moonsister.tcjy.main.model;

import com.moonsister.pay.tencent.PayBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.AuthenticationBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by x on 2016/8/30.
 */
public class ManorGrilActivityModelImpl implements ManorGrilActivityModel {
    @Override
    public void getAuthentication(EnumConstant.PayType payType, int sex, onLoadDateSingleListener<String> listener) {
//        Observable<AuthenticationBean> observable = ServerApi.getAppAPI().getAuthentication(payType.getType(), sex, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<AuthenticationBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.d(ManorGrilActivityModelImpl.this, e.getMessage());
//                        listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
//                    }
//
//                    @Override
//                    public void onNext(AuthenticationBean authenticationBean) {
//
//                    }
//
//                    @Override
//                    public void onNext(PayBean bean) {
//                        if (bean == null) {
//                            listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
//                        } else {
//                            String code = bean.getCode();
//                            if (StringUtis.equals(code, "1")) {
//                                AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), bean.getData().getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
//                                    @Override
//                                    public void onPayResult(int resultCode, String resultInfo) {
//                                        if (resultCode == 1) {
//                                            listener.onSuccess(UIUtils.getStringRes(R.string.pay_success), DataType.DATA_ZERO);
//                                        } else {
//                                            listener.onFailure(resultInfo);
//                                        }
//                                    }
//                                });
//                            } else if (StringUtis.equals(code, "1000")) {
//                                listener.onFailure(bean.getMsg());
//                                RxBus.getInstance().send(Events.EventEnum.LOGIN_CODE_TIMEOUT, null);
//                            } else
//                                listener.onFailure(bean.getMsg());
//                        }
//
//                    }
//                });
    }
}
