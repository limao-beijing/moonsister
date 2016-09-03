package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.RechargeBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.RechargeActivityModel;
import com.moonsister.tcjy.my.model.RechargeActivityModelImpl;
import com.moonsister.tcjy.my.view.RechargeActivityView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/9/3.
 */
public class RechargeActivityPersenterImpl implements RechargeActivityPersenter, BaseIModel.onLoadDateSingleListener<RechargeBean> {
    private RechargeActivityView view;
    private RechargeActivityModel model;
    @Override
    public void LoadData(String money, String pay_type) {
        view.showLoading();
        model.loadData(money,pay_type,this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(RechargeActivityView rechargeActivityView) {
        this.view = rechargeActivityView;
        model=new RechargeActivityModelImpl();
    }

    @Override
    public void onSuccess(RechargeBean rechargeBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (rechargeBean == null) {
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.success();
                break;
            case DATA_ONE:
                if (StringUtis.equals(rechargeBean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.success();
                        }
                    });
                } else {
                    view.transfePageMsg(rechargeBean.getMsg());
                }
                break;
        }
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
    }
}
