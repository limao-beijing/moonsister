package com.moonsister.tcjy.main.presenter;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.model.RedpacketAcitivityModel;
import com.moonsister.tcjy.main.model.RedpacketAcitivityModelImpl;
import com.moonsister.tcjy.main.view.PlayUserAcitivityView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/6/24.
 */
public class RedpacketAcitivityPresenterImpl implements RedpacketAcitivityPresenter, BaseIModel.onLoadDateSingleListener<String> {
    private PlayUserAcitivityView view;
    private RedpacketAcitivityModel model;
    private RedpacketAcitivityModel.PayType payType;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(PlayUserAcitivityView baseIView) {
        this.view = baseIView;
        model = new RedpacketAcitivityModelImpl();

    }

    @Override
    public void swicthAction(int id) {
        switch (id) {
            case R.id.action_back:
                view.pageFinish();
                break;
            case R.id.tv_weixin_play:
                view.weixinPay();
                break;
            case R.id.tv_aliplay_play:
                view.swicthAliPlay();
                break;
        }
    }

    private String moneyNumber;

    @Override
    public void aliPay(int type, String uid, String money) {
        view.showLoading();
        moneyNumber = money;
//        payType = RedpacketAcitivityModel.PayType.ALI_PAY;
        payType = RedpacketAcitivityModel.PayType.IAPP_PAY;
        model.pay(type, payType, uid, money, this);
    }

    @Override
    public void weixinPay(int type, String uid, String money) {
        view.showLoading();
        moneyNumber = money;
//        payType = RedpacketAcitivityModel.PayType.WX_PAY;
        payType = RedpacketAcitivityModel.PayType.IAPP_PAY;
        model.pay(type, payType, uid, money, this);
    }

    @Override
    public void onSuccess(String o, BaseIModel.DataType dataType) {
        switch (dataType) {
            case DATA_ZERO://支付宝
                if (StringUtis.equals("9000", o)) {
                    Events<String> events = new Events<String>();
                    events.what = Events.EventEnum.CHAT_SEND_REDPACKET_SUCCESS;
                    events.message = moneyNumber;
                    RxBus.getInstance().send(events);
                    view.transfePageMsg(UIUtils.getStringRes(R.string.pay_success));
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.pageFinish();
                        }
                    });
                } else if (StringUtis.equals("8000", o))
                    view.transfePageMsg(UIUtils.getStringRes(R.string.pay_affirm));
                else view.transfePageMsg(UIUtils.getStringRes(R.string.pay_failure));
                view.hideLoading();
                break;
            case DATA_ONE://微信支付
                UIUtils.sendDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.hideLoading();
                    }
                }, 5000);
                break;
            case DATA_TWO://爱贝支付
                if (StringUtis.equals(o, "1")) {
                    view.transfePageMsg(UIUtils.getStringRes(R.string.pay_success));
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.pageFinish();
                        }
                    });
                } else {
                    view.transfePageMsg(UIUtils.getStringRes(R.string.pay_failure));
                }
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
