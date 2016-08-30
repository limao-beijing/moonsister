package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by x on 2016/8/30.
 */
public interface ManorGrilActivityModel extends BaseIModel {
    void getAuthentication(EnumConstant.PayType payType, int sex, onLoadDateSingleListener<String> listener);
}
