package com.moonsister.tcjy.login.model;


import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;

/**
 * Created by pc on 2016/6/14.
 */
public interface RegiterFragmentModel extends BaseIModel {
    void loadSubmit(String phoneNumber, String code, onLoadDateSingleListener<BaseBean> listenter);

    void loadSecurity(String phoneMunber, onLoadDateSingleListener<BaseBean> listener);

//    public interface onLoadSubmitListenter<T> {
//
//        void onSubmitSuccess(T t);
//
//
//        void onSubmitFailure(String msg, Exception e);
//    }

}
