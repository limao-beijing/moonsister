package com.hyphenate.easeui.mvp.model;

import android.app.Activity;
import android.text.TextUtils;

import com.hickey.network.ModuleServerApi;
import com.hickey.network.bean.resposen.ChargeMessagePayBean;
import com.hickey.pay.aibeipay.AiBeiPayManager;
import com.hickey.tool.base.BaseHttpFunc;
import com.hickey.tool.base.BaseResponse;
import com.hickey.tool.constant.EnumConstant;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/11/26.
 */
public class ChargeMessageDialogModelImpl implements ChargeMessageDialogModel {
    @Override
    public void pay(final Activity activity, EnumConstant.PayType type, String lid, String acthcode, final onLoadDateSingleListener<Long> listener) {
        Observable<BaseResponse<ChargeMessagePayBean>> observable = ModuleServerApi.getAppAPI().getChargePay("1", "1", lid, acthcode);
        observable.map(new BaseHttpFunc<ChargeMessagePayBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ChargeMessagePayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(final ChargeMessagePayBean response) {
                        if (response == null) {
                            listener.onFailure("支付失败");
                        } else if (TextUtils.equals(response.getType(), "1")) {
                            listener.onSuccess(response.getExpire_time(), DataType.DATA_ZERO);
                        } else if (TextUtils.equals(response.getType(), "2")) {
                            AiBeiPayManager.getInstance().pay(activity, response.getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                @Override
                                public void onPayResult(int resultCode, String resultInfo) {
                                    if (resultCode == 1) {
                                        listener.onSuccess(response.getExpire_time(), DataType.DATA_ZERO);
                                    } else {
                                        listener.onFailure("支付失败");
                                    }
                                }
                            });
                        } else {
                            listener.onFailure("支付失败");
                        }
                    }
                });

//        Observable<BaseResponse<String>> observable = ModuleServerApi.getAppAPI().getChargePay(lid, acthcode)
//                .flatMap(new Func1<BaseResponse<ChargeMessagePayBean>, Observable<BaseResponse<String>>>() {
//                    @Override
//                    public Observable<BaseResponse<String>> call(BaseResponse<ChargeMessagePayBean> response) {
//                        if (response.getCode() == 1) {
//                            AiBeiPayManager.getInstance().pay();
//                        } else {
//
//                        }
//                        return null;
//                    }
//                });
//        ObservableUtis.$(observable, DataType.DATA_ZERO, listener);
    }
}
