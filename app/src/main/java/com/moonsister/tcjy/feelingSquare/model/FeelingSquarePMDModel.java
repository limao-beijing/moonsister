package com.moonsister.tcjy.feelingSquare.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.FeelingSquarePMDBean;

/**
 * Created by Administrator on 2016/9/21.
 */
public interface FeelingSquarePMDModel {
    void getCotent(String content, BaseIModel.onLoadDateSingleListener<FeelingSquarePMDBean> listener);
}
