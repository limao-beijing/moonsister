package com.moonsister.tcjy.engagement.model;

import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.EngagemengOrderBean;
import com.hickey.network.bean.EngagementTextBane;
import com.hickey.network.bean.PayBean;
import com.hickey.pay.aibeipay.AiBeiPayManager;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.AppConstant;
import com.hickey.network.ModuleServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.hickey.tool.ConfigUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.hickey.tool.widget.UIUtils;

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
        Observable<PayBean> observable = ModuleServerApi.getAppAPI().getEngagementOreder(dating_count, type.getType(), uid, money, date, address, message, EnumConstant.PayType.IAPP_PAY.getType(), "1", "1", "1",
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
                                EngagementTextBane bean = new EngagementTextBane();
                                bean.setCode(1 + "");
                                bean.setMsg("");
                                EngagementTextBane.DataBean dataBean = new EngagementTextBane.DataBean();
                                dataBean.setInfo(payBean.getData().getAbcode());
                                dataBean.setOrder_id(payBean.getData().getOrder_id());
                                bean.setData(dataBean);
                                listener.onSuccess(bean, DataType.DATA_ZERO);


                            } else if (StringUtis.equals(type, "2")) {
                                AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), payBean.getData().getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                    @Override
                                    public void onPayResult(int resultCode, String resultInfo) {
                                        if (StringUtis.equals(payBean.getCode(), "1")) {
                                            EngagementTextBane bean = new EngagementTextBane();
                                            bean.setCode(resultCode + "");
                                            bean.setMsg(resultInfo);
                                            EngagementTextBane.DataBean dataBean = new EngagementTextBane.DataBean();
                                            dataBean.setInfo(payBean.getData().getAbcode());
                                            dataBean.setOrder_id(payBean.getData().getOrder_id());
                                            bean.setData(dataBean);
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
        Observable<EngagemengOrderBean> observable = ModuleServerApi.getAppAPI().getEngagemengOrder(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
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
