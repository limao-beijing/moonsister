package com.moonsister.tcjy.main.model;

import com.hickey.network.bean.RegFourBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by x on 2016/9/1.
 */
public interface FillOutActivityModel extends BaseIModel {
    void fillout(String face,String nickname ,onLoadDateSingleListener<RegFourBean> listener);
    void submit(String address1,onLoadDateSingleListener listener);
}
