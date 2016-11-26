package com.moonsister.tcjy.engagement.view;

import com.hickey.network.bean.EngagementDetailsBean;
import com.hickey.tool.base.BaseIView;


/**
 * Created by jb on 2016/11/11.
 */
public interface EngagementDetailsView extends BaseIView {
    void setData(EngagementDetailsBean.DataBean bean);
}
