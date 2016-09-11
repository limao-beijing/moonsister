package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.PersonalReviseMessageBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.PersonalReviseActivityModel;
import com.moonsister.tcjy.my.model.PersonalReviseActivityModelImpl;
import com.moonsister.tcjy.my.view.PersonalReviseActivityView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/9/10.
 */
public class PersonalReviseActivityPersenterImpl implements PersonalReviseActivityPersenter,BaseIModel.onLoadDateSingleListener<PersonalReviseMessageBean> {
    private PersonalReviseActivityView view;
    private PersonalReviseActivityModel model;
    @Override
    public void sendPersonalReviseMessage(int uid) {
        view.showLoading();
        model.loadpersonalData(uid,this);
    }

    @Override
    public void sendUserJson(String contents) {
        view.showLoading();
        model.userJson(contents,this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(PersonalReviseActivityView personalReviseActivityView) {
        this.view = personalReviseActivityView;
        model=new PersonalReviseActivityModelImpl();
    }

    @Override
    public void onSuccess(PersonalReviseMessageBean personalReviseMessageBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (personalReviseMessageBean == null) {
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.success((PersonalReviseMessageBean) personalReviseMessageBean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(personalReviseMessageBean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.success((PersonalReviseMessageBean) personalReviseMessageBean);
                        }
                    });
                } else {
                    view.transfePageMsg(personalReviseMessageBean.getMsg());
                }
                break;
        }
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
    }
}
