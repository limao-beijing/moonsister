package com.moonsister.tcjy.center.model;

import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;


/**
 * Created by jb on 2016/6/29.
 */
public interface BuyDynamicRedPacketModel extends BaseIModel {
    void pay(String id, EnumConstant.PayType alipay, onLoadDateSingleListener<BaseBean> listener);

    void getPics(String id,onLoadDateSingleListener<BaseBean> listener);


    void loadVipRule(onLoadDateSingleListener<BaseBean> listener);
}
