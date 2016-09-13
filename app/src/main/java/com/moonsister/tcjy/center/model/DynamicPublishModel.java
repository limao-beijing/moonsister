package com.moonsister.tcjy.center.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.utils.EnumConstant;

import java.util.List;

/**
 * Created by jb on 2016/6/23.
 */
public interface DynamicPublishModel extends BaseIModel {
    void sendDynamicPics(EnumConstant.DynamicType dynamicType, String content, List<String> datas, String tags, String address, onLoadDateSingleListener defaultDynamicPresenter);

}
