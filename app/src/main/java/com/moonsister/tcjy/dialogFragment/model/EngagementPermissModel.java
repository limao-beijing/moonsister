package com.moonsister.tcjy.dialogFragment.model;

import com.hickey.network.bean.EngagementPermissTextBane;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/11/14.
 */
public interface EngagementPermissModel extends BaseIModel {
    void loadTextMsg(onLoadDateSingleListener<EngagementPermissTextBane> listener);
}
