package com.moonsister.tcjy.my.persenter;

import com.hickey.network.bean.PersonalMessageBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.PersonalActivityModel;
import com.moonsister.tcjy.my.model.PersonalActivityModelImpl;
import com.moonsister.tcjy.my.view.PersonalActivityView;

/**
 * Created by x on 2016/9/2.
 */
public class PersonalActivityPersenterImpl implements PersonalActivityPersenter, BaseIModel.onLoadDateSingleListener<PersonalMessageBean> {
    private PersonalActivityView view;
    private PersonalActivityModel model;
    @Override
    public void sendPersonalMessage(String uid) {
        view.showLoading();
        model.loadData(uid,this);
    }




    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(PersonalActivityView personalActivityView) {
        this.view = personalActivityView;
        model=new PersonalActivityModelImpl();
    }

    @Override
    public void onSuccess(PersonalMessageBean personalMessageBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (personalMessageBean == null) {
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.success((PersonalMessageBean) personalMessageBean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(personalMessageBean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.person();
                        }
                    });
                } else {
                    view.transfePageMsg(personalMessageBean.getMsg());
                }
                break;
        }

    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
    }
}
