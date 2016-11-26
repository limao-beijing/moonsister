package com.moonsister.tcjy.engagement.model;

import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/11/12.
 */
public interface EngagementActionModel extends BaseIModel {
    void actionEngagement(String id, int type, onLoadDateSingleListener<BaseBean> listener);

}
