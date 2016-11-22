package com.moonsister.tcjy.find.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.RankBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.UIUtils;

import rx.Observable;

/**
 * Created by jb on 2016/8/3.
 */
public class RankFragmentModelImpl implements RankFragmentModel {
    @Override
    public void loadData(int type, int page, onLoadListDateListener<RankBean.DataBean> listener) {
        Observable<RankBean> observable = ServerApi.getAppAPI().getRankData(type, page, 20, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RankBean>() {
            @Override
            public void onSuccess(RankBean rankBean) {
                if (rankBean != null) {
                    listener.onSuccess(rankBean.getData(), DataType.DATA_ZERO);
                } else {
                    listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                }
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }
}
