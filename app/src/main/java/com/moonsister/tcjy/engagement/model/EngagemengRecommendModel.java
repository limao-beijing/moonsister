package com.moonsister.tcjy.engagement.model;

import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;


/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengRecommendModel extends BaseIModel {
    void loadData(String userType, int page, EnumConstant.EngegamentType type, onLoadDateSingleListener<EngagemengRecommendBean> listener);
}
