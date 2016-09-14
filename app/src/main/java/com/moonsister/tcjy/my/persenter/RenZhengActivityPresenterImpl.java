package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BackTermsBean;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.my.model.RenZhengActivityModel;
import com.moonsister.tcjy.my.model.RenZhengActivityModelImpl;
import com.moonsister.tcjy.my.view.RenZhengActivityView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/9/3.
 */
public class RenZhengActivityPresenterImpl implements RenZhengAcivityPresenter, BaseIModel.onLoadDateSingleListener<BaseBean> {

    private RenZhengActivityView view;
    private RenZhengActivityModel model;

    @Override
    public void LoadData() {
        view.showLoading();
        model.loadData(this);
    }

    @Override
    public void submit(String address1, String address2, String text) {
        view.showLoading();
        model.submit(address1, address2, text, this);
    }

    @Override
    public void submitdata(String str, String order_id) {
        view.showLoading();
        model.submitdata(str, order_id, this);
    }


//    @Override
//    public void sendDynamic(EnumConstant.DynamicType dynamicType, String content, List<String> datas, String address) {
//        view.showLoading();
//        model.sendDynamicPics(dynamicType, content, datas, address, this);
//    }

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
        model = new RenZhengActivityModelImpl();
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {

        if (bean == null) {
            view.hideLoading();
            return;
        }

        switch (dataType) {
            case DATA_ZERO:
                view.success((BackTermsBean) bean);
                break;
            case DATA_ONE:
                if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                    UIUtils.sendDelayedOneMillis(new Runnable() {
                        @Override
                        public void run() {
                            view.finishPage();
                        }
                    });
                }
                view.transfePageMsg(bean.getMsg());

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
