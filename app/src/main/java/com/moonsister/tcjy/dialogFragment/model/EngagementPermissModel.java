package com.moonsister.tcjy.dialogFragment.model;

import com.hickey.network.bean.EngagementPermissTextBane;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by jb on 2016/11/14.
 */
public interface EngagementPermissModel extends BaseIModel {
    void loadTextMsg(onLoadDateSingleListener<EngagementPermissTextBane> listener);
}
