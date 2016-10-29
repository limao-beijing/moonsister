package com.moonsister.tcjy.center.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/6/29.
 */
public interface BuyDynamicRedPacketModel extends BaseIModel {
    void pay(String id, EnumConstant.PayType alipay, onLoadDateSingleListener<BaseBean> listener);

    void getPics(String id,onLoadDateSingleListener<BaseBean> listener);


    void loadVipRule(onLoadDateSingleListener<BaseBean> listener);
}
