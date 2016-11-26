package com.moonsister.tcjy.center.presenter;

import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.PayRedPacketPicsBean;
import com.hickey.network.bean.VipRule;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.center.model.BuyDynamicRedPacketModel;
import com.moonsister.tcjy.center.model.BuyDynamicRedPacketModelImpl;
import com.moonsister.tcjy.center.view.BuyDynamicRedPackketView;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;

/**
 * Created by jb on 2016/6/29.
 */
public class BuyDynamicRedPackketPersenterImpl implements BuyDynamicRedPackketPersenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private BuyDynamicRedPackketView view;
    private BuyDynamicRedPacketModel model;


    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(BuyDynamicRedPackketView payDynamicRedPackketView) {
        this.view = payDynamicRedPackketView;
        model = new BuyDynamicRedPacketModelImpl();
    }

    @Override
    public void alipay(String id) {
        view.showLoading();
        model.pay(id, EnumConstant.PayType.IAPP_PAY, this);

    }

    @Override
    public void weixinPay(String id) {
        view.showLoading();
        model.pay(id, EnumConstant.PayType.IAPP_PAY, this);
    }

    @Override
    public void getPics(String id) {
        view.showLoading();
        model.getPics(id, this);
    }

    @Override
    public void singBuy(String id) {
        view.showLoading();
        model.pay(id, EnumConstant.PayType.IAPP_PAY, this);
    }

    @Override
    public void loadVipRule() {
        view.showLoading();
        model.loadVipRule(this);
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {

        switch (dataType) {
            case DATA_ZERO:
                if (bean == null) {
                    view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
                } else {
                    if (StringUtis.equals(AppConstant.code_request_success, bean.getCode())) {
                        Events<PayRedPacketPicsBean> events = new Events<PayRedPacketPicsBean>();
                        events.what = Events.EventEnum.PAY_SUCCESS_GET_DATA;
                        events.message = (PayRedPacketPicsBean) bean;
                        RxBus.getInstance().send(events);
                        UIUtils.sendDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.finishPage();
                            }
                        }, 2000);

                    } else
                        view.transfePageMsg(bean.getMsg());

                }
                view.hideLoading();
                break;
            case DATA_ONE:
                UIUtils.sendDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.hideLoading();
                    }
                }, 5000);
                break;
            case DATA_FOUR:

                view.setVipRule((VipRule)bean);
                view.hideLoading();
                break;
        }


    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
