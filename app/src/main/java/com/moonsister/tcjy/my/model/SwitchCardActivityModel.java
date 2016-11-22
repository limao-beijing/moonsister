package com.moonsister.tcjy.my.model;

import com.hickey.network.bean.CardInfoBean;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by jb on 2016/7/4.
 */
public interface SwitchCardActivityModel extends BaseIModel{
    void loadCardInfo( onLoadDateSingleListener<CardInfoBean> listener);

}
