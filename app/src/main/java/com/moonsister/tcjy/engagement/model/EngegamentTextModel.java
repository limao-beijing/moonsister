package com.moonsister.tcjy.engagement.model;

import com.hickey.network.bean.EngagementTextBane;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;


/**
 * Created by jb on 2016/11/11.
 */
public interface EngegamentTextModel extends BaseIModel {
    void loadText(String datingID,EnumConstant.EngegamentTextType type, onLoadDateSingleListener<EngagementTextBane> listener);
}
