package com.moonsister.tcjy.main.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.FrientBaen;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.main.widget.RelationActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.UIUtils;

import rx.Observable;

/**
 * Created by jb on 2016/7/22.
 */
public class RelationActivityModelImpl implements RelationActivityModel {
    @Override
    public void loadData(int type, int page, String uid, onLoadDateSingleListener<FrientBaen> listener) {
        Observable<FrientBaen> observable = null;
        if (type == RelationActivity.WACTH_PAGE) {
            observable = ServerApi.getAppAPI().getWacthRelation(page, uid,UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);

        } else if (type == RelationActivity.FANS_PAGE) {
            observable = ServerApi.getAppAPI().getFenRelation(page, uid,UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID,AppConstant.API_VERSION);
        }
        if (observable == null) {
            listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
            return;
        }
        ObservableUtils.parser(observable, new ObservableUtils.Callback<FrientBaen>() {
            @Override
            public void onSuccess(FrientBaen baseBean) {
                listener.onSuccess(baseBean, DataType.DATA_FOUR);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

//    @Override
//    public void toup(String type, String to_uid, onLoadDateSingleListener<BaseBean> listener) {
//        Observable<PingbiBean> observable = ServerApi.getAppAPI().pingbibean(type, to_uid,UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID,AppConstant.API_VERSION);
//        ObservableUtils.parser(observable, new ObservableUtils.Callback<PingbiBean>() {
//            @Override
//            public void onSuccess(PingbiBean bean) {
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
