package com.moonsister.tcjy.my.persenter;

import com.hickey.network.bean.BalanceBean;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.MoneyActivityModel;
import com.moonsister.tcjy.my.model.MoneyActivityModelImpl;
import com.moonsister.tcjy.my.view.BalanceActivityView;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/9/2.
 */
public class MoneyActivityPersenterImpl implements MoneyActivityPersenter, BaseIModel.onLoadDateSingleListener<BalanceBean> {
    private BalanceActivityView view;
    private MoneyActivityModel model;

    @Override
    public void moneyba(int type, int page, int pagesize) {
        view.showLoading();
        model.money(type,page,pagesize,this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(BalanceActivityView balanceActivityView) {
        this.view = balanceActivityView;
        model=new MoneyActivityModelImpl();
    }

    @Override
    public void onSuccess(BalanceBean balanceBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (balanceBean == null) {
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.moneybalance((BalanceBean) balanceBean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(balanceBean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.moneybalance((BalanceBean) balanceBean);
                        }
                    });
                } else {
                    view.transfePageMsg(balanceBean.getMsg());
                }
                break;
        }
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
    }
}
