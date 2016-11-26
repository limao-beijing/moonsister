package com.moonsister.tcjy.my.persenter;

import com.hickey.network.bean.DefaultDataBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.AddCardActivityModel;
import com.moonsister.tcjy.my.model.AddCardActivityModelImpl;
import com.moonsister.tcjy.my.view.AddCardActivityView;

/**
 * Created by jb on 2016/7/3.
 */
public class AddCardActivityPersenterImpl implements AddCardActivityPersenter, BaseIModel.onLoadDateSingleListener<DefaultDataBean> {
    private AddCardActivityView view;
    private AddCardActivityModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(AddCardActivityView addCardActivityView) {
        this.view = addCardActivityView;
        model = new AddCardActivityModelImpl();

    }

    @Override
    public void submitAccount(String cardNumber, String username, String cardType, String bankname) {
        view.showLoading();
        model.submitAccount(cardNumber, username, cardType, bankname, this);
    }


    @Override
    public void onSuccess(DefaultDataBean defaultDataBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (StringUtis.equals(defaultDataBean.getCode(), AppConstant.code_request_success)) {
            RxBus.getInstance().send(Events.EventEnum.CARD_CHANGE, null);
            UIUtils.sendDelayedOneMillis(new Runnable() {
                @Override
                public void run() {
                    view.pageFinish();
                }
            });

        }
        view.transfePageMsg(defaultDataBean.getMsg());
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
