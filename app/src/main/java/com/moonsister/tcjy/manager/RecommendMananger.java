package com.moonsister.tcjy.manager;

import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.RecommendMemberFragmentBean;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.main.model.RecommendMemberFragmentModelImpl;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;

import java.util.ArrayList;

/**
 * Created by jb on 2016/8/15.
 */
public class RecommendMananger {
    private volatile static RecommendMananger instance;


    public static RecommendMananger getInstance() {
        if (instance == null) {
            synchronized (RecommendMananger.class) {
                if (instance == null) {
                    instance = new RecommendMananger();
                }

            }
        }
        return instance;
    }

    public void start() {
        RecommendMemberFragmentModelImpl model = new RecommendMemberFragmentModelImpl();
        model.recommend(new BaseIModel.onLoadDateSingleListener<RecommendMemberFragmentBean>() {
            @Override
            public void onSuccess(RecommendMemberFragmentBean bean, BaseIModel.DataType dataType) {
                if (bean != null && bean.getData() != null && bean.getData().size() > 0) {
                    ActivityUtils.startRecommendMemberActivity((ArrayList<RecommendMemberFragmentBean.DataBean>) bean.getData());
                }
            }

            @Override
            public void onFailure(String msg) {
                LogUtils.d("RecommendMananger", msg);
            }
        });
    }

    public void recommendMemberStatus(boolean islike, String uid) {
        RecommendMemberFragmentModelImpl model = new RecommendMemberFragmentModelImpl();
        model.getRecommend(islike ? 1 : 2, uid, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean defaultDataBean, BaseIModel.DataType dataType) {

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

}
