package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BackTermsBean;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.model.RenZhengActivityModel;
import com.moonsister.tcjy.my.model.RenZhengActivityModelImpl;
import com.moonsister.tcjy.my.view.RenZhengActivityView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/9/3.
 */
public class RenZhengActivityPresenterImpl implements RenZhengAcivityPresenter, BaseIModel.onLoadDateSingleListener<BackTermsBean> {

    private RenZhengActivityView view;
    private RenZhengActivityModel model;

    @Override
    public void LoadData() {
        view.showLoading();
        model.loadData(this);
    }

//    @Override
//    public void upLoadIcon(String iconPath) {
//        view.showLoading();
//        model.upLoadIcon(iconPath, this);
//    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(RenZhengActivityView renZhengActivityView) {
        view = renZhengActivityView;
        model=new RenZhengActivityModelImpl();
    }

    @Override
    public void onSuccess(BackTermsBean backTermsBean, BaseIModel.DataType dataType) {
        view.hideLoading();
        if (backTermsBean == null) {
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.success((BackTermsBean) backTermsBean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(backTermsBean.getCode(), AppConstant.code_request_success)) {
                    RxBus.getInstance().send(Events.EventEnum.MONEY_CHANGE, null);
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.success((BackTermsBean) backTermsBean);
                        }
                    });
                } else {
                    view.transfePageMsg(backTermsBean.getMsg());
                }
                break;
        }
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
    }
}
