package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.GetMoneyBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.GetMoneyModel;
import com.moonsister.tcjy.my.model.GetMoneyModelImpl;
import com.moonsister.tcjy.my.view.GetMoneyView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/7/3.
 */
public class GetMoneyPersenterImpl implements GetMoneyPersenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private GetMoneyView view;
    private GetMoneyModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(GetMoneyView getMoneyView) {
        this.view = getMoneyView;
        model = new GetMoneyModelImpl();

    }

    @Override
    public void loadbasicInfo() {
        view.showLoading();
        model.loadbasicInfo(this);
    }

    @Override
    public void PaySubmit(String number, int money) {
        view.showLoading();
        model.paySubmit(number, money, this);
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {

        if (bean == null) {
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.setBasicInfo((GetMoneyBean) bean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.pageFinish();
                        }
                    }, 2000);
                }
                view.transfePageMsg(bean.getMsg());
                break;
        }
        view.hideLoading();


    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);

    }
}
