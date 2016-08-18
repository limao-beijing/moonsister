package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.RecommendMemberFragmentBean;

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
