package com.moonsister.tcjy.my.persenter;

import com.hickey.network.bean.UserDetailBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.my.model.HreatFragmentModel;
import com.moonsister.tcjy.my.model.HreatFragmentModelImpl;
import com.moonsister.tcjy.my.view.HreatFragmentView;

/**
 * Created by x on 2016/9/8.
 */
public class HreatFragmentPresenterImpl implements HreatFragmentPersenter, BaseIModel.onLoadDateSingleListener<UserDetailBean> {
    private HreatFragmentView view;
    private HreatFragmentModel model;

    @Override
    public void PaySubmit(String uid) {
        view.showLoading();
        model.loadData(uid,this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(HreatFragmentView hreatFragmentView) {
        this.view = hreatFragmentView;
        model=new HreatFragmentModelImpl();
    }

    @Override
    public void onSuccess(UserDetailBean userDetailBean, BaseIModel.DataType dataType) {
        if (StringUtis.equals(userDetailBean.getCode(), AppConstant.code_request_success)) {
            view.success(userDetailBean);
        } else {
            view.transfePageMsg(userDetailBean.getMsg());
        }

        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }


}
