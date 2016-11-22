package com.moonsister.tcjy.my.model;


import com.hickey.network.ServerApi;
import com.hickey.network.bean.BackInsertBean;
import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.InsertBaen;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by x on 2016/8/27.
 */
public class InsertActivityImpl implements InsertActivityModel {

    @Override
    public void loadData(int tagid, String tagname, int img, onLoadDateSingleListener<BaseBean> listener) {
        Observable<InsertBaen> observable = ServerApi.getAppAPI().getInsertRelation(tagid,tagname,img, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

//    @Override
//    public void sendData(int tlist, InsertActivityPersenterImpl listener) {
//        Observable<BackInsertBean> observable = ServerApi.getAppAPI().makeInsertBean(tlist, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID ,AppConstant.API_VERSION);
//        ObservableUtils.parser(observable, new ObservableUtils.Callback<BackInsertBean>() {
//            @Override
//            public void onSuccess(BackInsertBean bean) {
//                listener.onSuccess(bean, DataType.DATA_ONE);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                listener.onFailure(msg);
//            }
//        });
//    }

    @Override
    public void sendData(String tlist, onLoadDateSingleListener<BaseBean> listener) {
        Observable<BackInsertBean> observable = ServerApi.getAppAPI().makeInsertBean(tlist, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID ,AppConstant.API_VERSION);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean basebean) {
                listener.onSuccess(basebean, DataType.DATA_ONE);
            }

            @Override
            public void onFailure(String msg) {

            }

    });
    }
//    @Override
//    public void sendData(EnumConstant.PayType iappPay, int tagid, onLoadDateSingleListener<InsertBaen> listener) {
//        Observable<InsertBaen> observable = ServerApi.getAppAPI().getInsertRelation(tagid,UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
//        ObservableUtils.parser(observable, new ObservableUtils.Callback<InsertBaen>() {
//            @Override
//            public void onSuccess(InsertBaen bean) {
//                listener.onSuccess(bean, DataType.DATA_ZERO);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                listener.onFailure(msg);
//            }
//        });
//    }
}
