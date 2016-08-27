package com.moonsister.tcjy.my.model;

import com.moonsister.pay.tencent.PayBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;

/**
 * Created by x on 2016/8/27.
 */
public class InsertActivityImpl implements InsertActivityModel {

    @Override
    public void loadData(int tagid, String tagname, int img, onLoadDateSingleListener<InsertBaen> listener) {
        Observable<InsertBaen> observable = ServerApi.getAppAPI().getInsertRelation(tagid,tagname,img, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<InsertBaen>() {
            @Override
            public void onSuccess(InsertBaen bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
