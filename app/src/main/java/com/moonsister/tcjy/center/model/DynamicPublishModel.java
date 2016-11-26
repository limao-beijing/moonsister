package com.moonsister.tcjy.center.model;


import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;

import java.util.List;

/**
 * Created by jb on 2016/6/23.
 */
public interface DynamicPublishModel extends BaseIModel {
    void sendDynamicPics(EnumConstant.DynamicType dynamicType, String content, List<String> datas, String tags, String address, onLoadDateSingleListener defaultDynamicPresenter);

}
