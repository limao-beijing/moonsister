package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.RegFourBean;

/**
 * Created by x on 2016/9/1.
 */
public interface FillOutActivityModel extends BaseIModel {
    void fillout(String face,String nickname ,onLoadDateSingleListener<RegFourBean> listener);
}
