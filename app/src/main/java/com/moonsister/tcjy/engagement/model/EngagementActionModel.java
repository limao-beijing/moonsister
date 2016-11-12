package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;

/**
 * Created by jb on 2016/11/12.
 */
public interface EngagementActionModel extends BaseIModel {
    void actionEngagement(String id, int type, onLoadDateSingleListener<BaseBean> listener);

}
