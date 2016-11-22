package com.moonsister.tcjy.my.persenter;

import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.InsertBaen;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.InsertActivityImpl;
import com.moonsister.tcjy.my.model.InsertActivityModel;
import com.moonsister.tcjy.my.view.InsertActivityView;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/8/27.
 */
public class InsertActivityPersenterImpl implements InsertActivityPersenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private InsertActivityView view;
    private InsertActivityModel model;


    @Override
    public void LoadData(int tagid,String tagname,int img) {
        view.showLoading();
        model.loadData(tagid,tagname,img,this);
    }

    @Override
    public void sendData(String tlist) {
        view.showLoading();
        model.sendData(tlist, this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(InsertActivityView insertActivityView) {
        this.view = insertActivityView;
         model=new InsertActivityImpl();

    }


    @Override
    public void onSuccess(BaseBean baseBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (baseBean == null) {
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.setBasicInfo((InsertBaen) baseBean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(baseBean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.success();
                        }
                    });
                } else {
                    view.transfePageMsg(baseBean.getMsg());
                }
                break;
        }
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
    }
}
