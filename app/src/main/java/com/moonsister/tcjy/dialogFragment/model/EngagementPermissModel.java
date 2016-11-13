package com.moonsister.tcjy.dialogFragment.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.EngagementPermissTextBane;

/**
 * Created by jb on 2016/11/14.
 */
public interface EngagementPermissModel extends BaseIModel {
    void loadTextMsg(onLoadDateSingleListener<EngagementPermissTextBane> listener);
}
