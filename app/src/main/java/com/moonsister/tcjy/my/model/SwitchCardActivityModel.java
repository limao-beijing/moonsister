package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.CardInfoBean;

/**
 * Created by jb on 2016/7/4.
 */
public interface SwitchCardActivityModel extends BaseIModel{
    void loadCardInfo( onLoadDateSingleListener<CardInfoBean> listener);

}
