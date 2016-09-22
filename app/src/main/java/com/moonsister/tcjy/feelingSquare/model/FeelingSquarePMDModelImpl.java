package com.moonsister.tcjy.feelingSquare.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.FeelingSquarePMDBean;
import com.moonsister.tcjy.bean.RankBean;
import com.moonsister.tcjy.bean.model.FeelingSquareDisplayBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.UIUtils;

import rx.Observable;

/**
 * Created by Administrator on 2016/9/21.
 */
public class FeelingSquarePMDModelImpl implements FeelingSquarePMDModel{

    @Override
    public void getCotent(String content, BaseIModel.onLoadDateSingleListener<FeelingSquarePMDBean> listener) {
        Observable<FeelingSquarePMDBean> observable = ServerApi.getAppAPI().getPMDContent(content, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<FeelingSquarePMDBean>() {
            @Override
            public void onSuccess(FeelingSquarePMDBean feelingSquarePMDBean) {
                if (feelingSquarePMDBean == null) {
                    listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                } else {
                    listener.onSuccess(feelingSquarePMDBean, BaseIModel.DataType.DATA_ZERO);
                }
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }

        });
    }
}