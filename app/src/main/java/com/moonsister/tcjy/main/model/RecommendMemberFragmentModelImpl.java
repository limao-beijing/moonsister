package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.RecommendMemberFragmentBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/8/15.
 */
public class RecommendMemberFragmentModelImpl implements RecommendMemberFragmentModel {

    @Override
    public void recommend(onLoadDateSingleListener<RecommendMemberFragmentBean> listener) {
        int pagesize = 20;
        Observable<RecommendMemberFragmentBean> observable = ServerApi.getAppAPI().getRecommendMemberBean(pagesize, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<RecommendMemberFragmentBean>() {
            @Override
            public void onSuccess(RecommendMemberFragmentBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    public void getRecommend(int type, String uid, onLoadDateSingleListener<DefaultDataBean> listener) {
        ServerApi.getAppAPI().getRecommend(type, uid, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DefaultDataBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DefaultDataBean defaultDataBean) {

                    }
                });

    }
}
