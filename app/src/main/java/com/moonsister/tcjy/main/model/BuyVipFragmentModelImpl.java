package com.moonsister.tcjy.main.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.PayBean;
import com.hickey.pay.aibeipay.AiBeiPayManager;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/8/13.
 */
public class BuyVipFragmentModelImpl implements BuyVipFragmentModel {


    @Override
    public void buyVIP(EnumConstant.PayType payType, int number, String phone, onLoadDateSingleListener<String> listener) {
        Observable<PayBean> observable = ServerApi.getAppAPI().getBuyVIP(payType.getType(), number,phone, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(BuyVipFragmentModelImpl.this, e.getMessage());
                        listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                    }

                    @Override
                    public void onNext(PayBean bean) {
                        if (bean == null) {
                            listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                        } else {
                            String code = bean.getCode();
                            if (StringUtis.equals(code, "1")) {
                                AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), bean.getData().getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                    @Override
                                    public void onPayResult(int resultCode, String resultInfo) {
                                        if (resultCode == 1) {
                                            listener.onSuccess(UIUtils.getStringRes(R.string.pay_success), DataType.DATA_ZERO);
                                        } else {
                                            listener.onFailure(resultInfo);
                                        }
                                    }
                                });
                            } else if (StringUtis.equals(code, "1000")) {
                                listener.onFailure(bean.getMsg());
                                RxBus.getInstance().send(Events.EventEnum.LOGIN_CODE_TIMEOUT, null);
                            } else
                                listener.onFailure(bean.getMsg());
                        }

                    }
                });

    }
}
