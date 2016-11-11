package com.moonsister.appointment.engagement.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.EngagemengRecommendBean;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengRecommendModel extends BaseIModel {
    void loadData(int page, EnumConstant.EngegamentType type, onLoadDateSingleListener<EngagemengRecommendBean> listener);
}
