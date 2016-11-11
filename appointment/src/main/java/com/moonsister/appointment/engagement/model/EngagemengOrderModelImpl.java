package com.moonsister.appointment.engagement.model;

import com.moonsister.pay.aibeipay.AiBeiPayManager;
import com.moonsister.pay.tencent.PayBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.EngagemengOrderBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/9/27.
 */
public class EngagemengOrderModelImpl implements EngagemengOrderModel {
    @Override
    public void submitData(String dating_count, EnumConstant.EngegamentType type, String uid, String money, String date,
                           String message, String address, onLoadDateSingleListener<BaseBean> listener) {
        Observable<PayBean> observable = ServerApi.getAppAPI().getEngagementOreder(dating_count, type.getType(), uid, money, date, address, message, EnumConstant.PayType.IAPP_PAY.getType(), "1", "1",
                UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                    }

                    @Override
                    public void onNext(PayBean payBean) {
                        if (StringUtis.equals(payBean.getCode(), "1")) {
                            String type = payBean.getData().getType();
                            if (StringUtis.equals(type, "1") || StringUtis.equals(type, "5")) {
                                BaseBean bean = new BaseBean();
                                bean.setCode("1");
                                bean.setMsg(payBean.getMsg());
                                listener.onSuccess(bean, DataType.DATA_ZERO);
                            } else if (StringUtis.equals(type, "2")) {
                                AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), payBean.getData().getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                    @Override
                                    public void onPayResult(int resultCode, String resultInfo) {
                                        if (StringUtis.equals(payBean.getCode(), "1")) {
                                            BaseBean bean = new BaseBean();
                                            bean.setCode(resultCode + "");
                                            bean.setMsg(resultInfo);
                                            listener.onSuccess(bean, DataType.DATA_ZERO);
                                        } else {
                                            listener.onFailure(payBean.getMsg());
                                        }
                                    }
                                });
                            } else listener.onFailure(payBean.getMsg());

                        } else {
                            listener.onFailure(payBean.getMsg());
                        }
                    }
                });
    }

    @Override
    public void loadData(onLoadDateSingleListener<BaseBean> listenter) {
        Observable<EngagemengOrderBean> observable = ServerApi.getAppAPI().getEngagemengOrder(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                listenter.onSuccess(bean, DataType.DATA_ONE);
            }

            @Override
            public void onFailure(String msg) {
                listenter.onFailure(msg);
            }
        });
    }
}
