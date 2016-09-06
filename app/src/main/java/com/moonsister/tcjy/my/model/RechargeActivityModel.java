package com.moonsister.tcjy.my.model;

import com.moonsister.pay.tencent.PayBean;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.RechargeBean;


/**
 * Created by x on 2016/9/3.
 */
public interface RechargeActivityModel extends BaseIModel {
    void loadData(String money,String pay_type, onLoadDateSingleListener<RechargeBean> listener);

}
