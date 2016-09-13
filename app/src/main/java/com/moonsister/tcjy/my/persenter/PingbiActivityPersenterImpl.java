package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BackTermsBean;
import com.moonsister.tcjy.bean.PingbiBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.PingbiActivityModel;
import com.moonsister.tcjy.my.model.PingbiActivityModelImpl;
import com.moonsister.tcjy.my.view.PingbiView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/9/14.
 */
public class PingbiActivityPersenterImpl implements PingbiActivityPersenter,BaseIModel.onLoadDateSingleListener<PingbiBean>  {
    private PingbiView view;
    private PingbiActivityModel model;
    @Override
    public void submit(String page) {
        view.showLoading();
        model.loadData(page,this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(PingbiView pingbiView) {
        view = pingbiView;
        model = new PingbiActivityModelImpl();
    }

    @Override
    public void onSuccess(PingbiBean pingbiBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (pingbiBean == null) {
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.success((PingbiBean)pingbiBean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(pingbiBean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.success((PingbiBean)pingbiBean);
                        }
                    });
                } else {
                    view.transfePageMsg(pingbiBean.getMsg());
                }
                break;
        }
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
