package com.moonsister.tcjy.login.presenter;


import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.RegiterBean;
import com.hickey.tool.ConfigUtils;
import com.hickey.tool.base.BaseIModel;
import com.moonsister.tcjy.login.model.RegiterFragmentModel;
import com.moonsister.tcjy.login.model.RegiterFragmentModelImpl;
import com.moonsister.tcjy.login.view.RegiterFragmentView;
import com.moonsister.tcjy.utils.LogUtils;

/**
 * Created by pc on 2016/6/14.
 */
public class RegiterFragmentPresenerImpl implements RegiterFragmentPresener, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private RegiterFragmentView view;
    private RegiterFragmentModel fragmentModel;

    @Override
    public void getSecurityCode(String phoneNumer) {
        view.showLoading();
        fragmentModel.loadSecurity(phoneNumer, this);

    }

    @Override
    public void submitRegiter(String phoneNumber, String code) {
        view.showLoading();
        fragmentModel.loadSubmit(phoneNumber, code, this);

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(RegiterFragmentView regiterFragmentView) {
        this.view = regiterFragmentView;
        fragmentModel = new RegiterFragmentModelImpl();
    }


    @Override
    public void onSuccess(BaseBean baseBean, BaseIModel.DataType type) {
        switch (type) {
            case DATA_ZERO:
                if (baseBean != null) {
                    if ("1".equals(baseBean.getCode()))
                        view.LoopMsg();
                    view.requestFailed(baseBean.getMsg());
                }
                break;
            case DATA_ONE:
                if ("1".equals(baseBean.getCode()) && baseBean instanceof RegiterBean)
                    ConfigUtils.getInstance().getMainHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.navigationNext(((RegiterBean) baseBean).getData().getAuthcode());
                        }
                    }, 1000);


                break;
        }
        view.hideLoading();

    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.requestFailed(msg);
    }

//
//    @Override
//    public void onSubmitSuccess(RegiterBean baseBean) {
//        view.hideLoading();
//        if (baseBean != null) {
//            view.requestFailed(baseBean.getMsg());
//            if ("1".equals(baseBean.getCode()))
//                ConfigUtils.getInstance().getMainHandler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        LogUtils.e(RegiterFragmentPresenerImpl.class,"RegiterCode  : " +baseBean.getData().toString());
//                        view.navigationNext(baseBean.getData().getAuthcode());
//                    }
//                },1000);
//
//
//
//        }
//
//
//    }
//
//    @Override
//    public void onSubmitFailure(String msg, Exception e) {
//        view.hideLoading();
//        view.requestFailed(msg);
//    }

}
