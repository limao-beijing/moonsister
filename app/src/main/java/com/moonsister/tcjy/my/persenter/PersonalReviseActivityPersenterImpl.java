package com.moonsister.tcjy.my.persenter;

import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.PersonalReviseMessageBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.PersonalReviseActivityModel;
import com.moonsister.tcjy.my.model.PersonalReviseActivityModelImpl;
import com.moonsister.tcjy.my.view.PersonalReviseActivityView;

/**
 * Created by x on 2016/9/10.
 */
public class PersonalReviseActivityPersenterImpl implements PersonalReviseActivityPersenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private PersonalReviseActivityView view;
    private PersonalReviseActivityModel model;

    @Override
    public void sendPersonalReviseMessage(String uid) {
        view.showLoading();
        model.loadpersonalData(uid, this);
    }

    @Override
    public void sendUserJson(String contents) {
        view.showLoading();
        model.userJson(contents, this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(PersonalReviseActivityView personalReviseActivityView) {
        this.view = personalReviseActivityView;
        model = new PersonalReviseActivityModelImpl();
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (bean == null) {
            view.hideLoading();
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                if (bean instanceof PersonalReviseMessageBean)
                    view.success((PersonalReviseMessageBean) bean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.submitSuccess();
                        }
                    });
                    view.transfePageMsg(bean.getMsg());
                }
                break;
        }
        view.hideLoading();

    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
