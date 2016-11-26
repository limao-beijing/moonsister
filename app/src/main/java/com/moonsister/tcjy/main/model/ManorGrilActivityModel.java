package com.moonsister.tcjy.main.model;

import com.hickey.network.bean.RegOneBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by x on 2016/8/30.
 */
public interface ManorGrilActivityModel extends BaseIModel {
//    void getRegOneBean(EnumConstant.PayType payType, int sex, onLoadDateSingleListener<String> listener);
    void regone(int sex,onLoadDateSingleListener<RegOneBean> listener);
}
