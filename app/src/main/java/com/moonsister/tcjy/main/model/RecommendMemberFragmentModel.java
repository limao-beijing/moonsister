package com.moonsister.tcjy.main.model;

import com.hickey.network.bean.RecommendMemberFragmentBean;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by jb on 2016/8/15.
 */
public interface RecommendMemberFragmentModel extends BaseIModel {
    /**
     * 认证
     *
     * @param listener
     */
    void recommend(onLoadDateSingleListener<RecommendMemberFragmentBean> listener);
}
