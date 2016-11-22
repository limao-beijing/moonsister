package com.moonsister.tcjy.main.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.DynamicContent;
import com.hickey.network.bean.PayBean;
import com.hickey.network.bean.RegFourBean;
import com.hickey.pay.aibeipay.AiBeiPayManager;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by x on 2016/9/1.
 */
public class FillOutActivityModelImpl implements FillOutActivityModel {
    @Override
    public void fillout(String face, String nickname, onLoadDateSingleListener<RegFourBean> listener) {
        Observable<RegFourBean> observable = ServerApi.getAppAPI().getRegFourBean(face, nickname, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RegFourBean>() {
            @Override
            public void onSuccess(RegFourBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void submit(String address1, onLoadDateSingleListener listener) {
        ArrayList<DynamicContent> aliyunPtahs = new ArrayList<DynamicContent>();
        Observable<PayBean> observable = ServerApi.getAppAPI().getCertificationPay(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<PayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                    }

                    @Override
                    public void onNext(PayBean bean) {

                        if (bean == null) {
                            listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                        } else if (StringUtis.equals(bean.getCode(), "1000")) {
                            listener.onFailure(UIUtils.getStringRes(R.string.login_code_timeout));
                            RxBus.getInstance().send(Events.EventEnum.LOGIN_CODE_TIMEOUT, null);
                        } else {
                            if (StringUtis.equals("1", bean.getCode())) {
                                AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), bean.getData().getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                    @Override
                                    public void onPayResult(int resultCode, String resultInfo) {
                                        if (resultCode == 1) {
                                            listener.onSuccess(bean, DataType.DATA_ONE);
                                        } else {
                                            listener.onFailure(resultInfo);
                                        }

                                    }
                                });
                            } else
                                listener.onFailure(bean.getMsg());
                        }
                    }

                });
    }
}
