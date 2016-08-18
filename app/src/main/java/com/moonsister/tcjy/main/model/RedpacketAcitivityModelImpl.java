package com.moonsister.tcjy.main.model;

import com.moonsister.pay.aibeipay.AiBeiPayManager;
import com.moonsister.pay.aliyun.AliPayManager;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.pay.tencent.PayBean;
import com.moonsister.pay.tencent.WeixinManager;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.wxapi.WXPayEntryActivity;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/6/24.
 */
public class RedpacketAcitivityModelImpl implements RedpacketAcitivityModel {


    @Override
    public void pay(int type, PayType playType, String uid, String money, onLoadDateSingleListener listener) {
        String authcode = UserInfoManager.getInstance().getAuthcode();
        Observable<PayBean> pay = null;
        if (type == 1) {
            pay = ServerApi.getAppAPI().getredPacketIndent(playType.getType(), uid, money, authcode, AppConstant.CHANNEL_ID);
        } else if (type == 2) {
            pay = ServerApi.getAppAPI().getFlowerIndent(playType.getType(), uid, money, authcode, AppConstant.CHANNEL_ID);
        }
        if (pay != null) {
            pay.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<PayBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            listener.onFailure(UIUtils.getStringRes(R.string.network_error));
                        }

                        @Override
                        public void onNext(PayBean payBean) {

                            if (payBean == null) {
                                listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                                return;
                            }
                            if (StringUtis.equals("1000", payBean.getCode())) {
                                listener.onFailure(UIUtils.getStringRes(R.string.code_timeout));
                                RxBus.getInstance().send(Events.EventEnum.LOGIN_CODE_TIMEOUT, null);
                                return;
                            }
                            if (!StringUtis.equals(AppConstant.code_request_success, payBean.getCode())) {
                                listener.onFailure(payBean.getMsg());
                                return;
                            }
                            if (StringUtis.equals(payBean.getCode(), AppConstant.code_request_success) && payBean.getData() != null) {
                                PayBean.DataBean data = payBean.getData();
                                startAliPlayApp(data, playType, listener);
                            } else {
                                listener.onFailure(payBean.getMsg());
                            }


                        }
                    });


//            ObservableUtils.parser(pay, new ObservableUtils.Callback<PayBean>() {
//                @Override
//                public void onSuccess(PayBean bean) {
//                    if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success) && bean.getData() != null) {
//                        PayBean.DataBean data = bean.getData();
//                        startAliPlayApp(data, playType, listener);
//                    } else {
//                        listener.onFailure(bean.getMsg());
//                    }
//                }
//
//                @Override
//                public void onFailure(String msg) {
//                    listener.onFailure(msg);
//                }
//            });

        }


    }

    private void startAliPlayApp(PayBean.DataBean data, PayType playType, onLoadDateSingleListener listener) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    if (playType == PayType.ALI_PAY) {
                        String play = AliPayManager.getInstance().play(ConfigUtils.getInstance().getActivityContext(), data.getAlicode());
                        subscriber.onNext(play);
                    } else if (playType == PayType.WX_PAY) {
                        WeixinManager.getInstance(ConfigUtils.getInstance().getApplicationContext(), WXPayEntryActivity.APP_ID).pay(data);
                        listener.onSuccess(null, DataType.DATA_ONE);
                        subscriber.onCompleted();
                    } else if (playType == PayType.IAPP_PAY) {
                        UIUtils.onRunMainThred(new Runnable() {
                            @Override
                            public void run() {
                                AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), data.getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                    @Override
                                    public void onPayResult(int resultCode, String resultInfo) {
                                        if (resultCode == 1) {
                                            listener.onSuccess(resultCode + "", DataType.DATA_TWO);
                                        } else if (resultCode == 4) {
                                            listener.onFailure(resultInfo);
                                        } else {
                                            listener.onFailure(UIUtils.getStringRes(R.string.pay_failure));
                                        }
                                        subscriber.onCompleted();
                                    }
                                });
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        UIUtils.getStringRes(R.string.network_error);
                    }

                    @Override
                    public void onNext(String s) {
                        listener.onSuccess(s, DataType.DATA_ZERO);
                    }
                });
    }


}
