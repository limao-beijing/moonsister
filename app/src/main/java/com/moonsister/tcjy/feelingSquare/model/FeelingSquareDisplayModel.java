package com.moonsister.tcjy.feelingSquare.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.model.FeelingSquareDisplayBean;


/**
 * Created by Administrator on 2016/9/20.
 */
public interface FeelingSquareDisplayModel extends BaseIModel{
    void loadData(onLoadListDateListener<FeelingSquareDisplayBean> listener);

}
