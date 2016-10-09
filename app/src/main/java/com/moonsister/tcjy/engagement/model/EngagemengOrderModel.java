package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengOrderModel extends BaseIModel {
    void submitData(EnumConstant.EngegamentType type, String uid, String money, String date, String message, String address, onLoadDateSingleListener<BaseBean> listener);
}
