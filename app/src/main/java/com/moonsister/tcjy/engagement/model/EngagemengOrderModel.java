package com.moonsister.tcjy.engagement.model;

import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;


/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengOrderModel extends BaseIModel {
    void submitData(String dating_count, EnumConstant.EngegamentType type, String uid, String money, String date, String message, String address, onLoadDateSingleListener<BaseBean> listener);

    void loadData(onLoadDateSingleListener<BaseBean> listenter);
}
