package com.moonsister.tcjy.my.model;

import com.hickey.network.bean.BalanceBean;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by x on 2016/9/2.
 */
public interface MoneyActivityModel extends BaseIModel {
    void money(int type,int page,int pagesize,onLoadDateSingleListener<BalanceBean> listener);
}
