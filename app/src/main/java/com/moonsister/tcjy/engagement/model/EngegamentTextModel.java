package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.EngagementTextBane;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/11/11.
 */
public interface EngegamentTextModel extends BaseIModel {
    void loadText(String datingID,EnumConstant.EngegamentTextType type, onLoadDateSingleListener<EngagementTextBane> listener);
}
