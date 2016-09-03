package com.moonsister.tcjy.my.model;

import android.content.Intent;

import com.moonsister.pay.aibeipay.AiBeiPayManager;
import com.moonsister.pay.tencent.PayBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.RechargeBean;
import com.moonsister.tcjy.login.widget.RegActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.widget.MoneyActivity;
import com.moonsister.tcjy.my.widget.RechargeActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.StringUtis;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by x on 2016/9/3.
 */
public class RechargeActivityModelImpl implements RechargeActivityModel {
    @Override
    public void loadData(String money, String pay_type, onLoadDateSingleListener<RechargeBean> listener) {
        Observable<PayBean> observable = ServerApi.getAppAPI().getrechargeBean(money,pay_type, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID,AppConstant.API_VERSION);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PayBean payBean) {
                        if (StringUtis.equals(payBean.getCode(),"1")){
                            AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), payBean.getData().getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                @Override
                                public void onPayResult(int resultCode, String resultInfo) {
                                    if(StringUtis.equals(payBean.getCode(),"1")){
                                        ActivityUtils.startRechaargeMoneyActivity();
                                    }else{
                                        listener.onFailure(payBean.getMsg());
                                    }
                                }
                            });
                        }else {
                            listener.onFailure(payBean.getMsg());
                        }
                    }
                });
//       ObservableUtils.parser(observable, new ObservableUtils.Callback<RechargeBean>() {
//            @Override
//            public void onSuccess(RechargeBean bean) {
////
//                listener.onSuccess(bean, DataType.DATA_ZERO);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                listener.onFailure(msg);
//            }
//        });
    }
}
