package com.moonsister.tcjy.my.model;

import com.hickey.network.bean.WithdRawDepositBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/7/2.
 */
public interface WithdRawDepositModel extends BaseIModel {
    void loadEnableMoney(onLoadDateSingleListener<WithdRawDepositBean> listener);
}
