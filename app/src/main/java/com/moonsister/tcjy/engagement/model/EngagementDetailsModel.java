package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.EngagementDetailsBean;

/**
 * Created by jb on 2016/11/11.
 */
public interface EngagementDetailsModel extends BaseIModel {
    void loadDate(String id, onLoadDateSingleListener<EngagementDetailsBean> listener);

    void loadByOrderIdData(String id, onLoadDateSingleListener<EngagementDetailsBean> listener);

    void loadByIdData2(String id, onLoadDateSingleListener<EngagementDetailsBean> listener);

}
