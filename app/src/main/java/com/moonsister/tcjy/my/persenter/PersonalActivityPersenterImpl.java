package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.PersonalMessageBean;
import com.moonsister.tcjy.bean.PersonalMessageFenBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.PersonalActivityModel;
import com.moonsister.tcjy.my.model.PersonalActivityModelImpl;
import com.moonsister.tcjy.my.view.PersonalActivityFenView;
import com.moonsister.tcjy.my.view.PersonalActivityView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/9/2.
 */
public class PersonalActivityPersenterImpl implements PersonalActivityPersenter, BaseIModel.onLoadDateSingleListener<PersonalMessageFenBean> {
    private PersonalActivityFenView view;
    private PersonalActivityModel model;

    @Override
    public void sendPersonalMessageFen(String uid, String get_source) {
        view.showLoading();
        model.loadData(uid,get_source,this);
    }



    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(PersonalActivityFenView personalActivityFenView) {
        this.view = personalActivityFenView;
        model=new PersonalActivityModelImpl();
    }

    @Override
    public void onSuccess(PersonalMessageFenBean personalMessageFenBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (personalMessageFenBean == null) {
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.success((PersonalMessageFenBean) personalMessageFenBean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(personalMessageFenBean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.person();
                        }
                    });
                } else {
                    view.transfePageMsg(personalMessageFenBean.getMsg());
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
