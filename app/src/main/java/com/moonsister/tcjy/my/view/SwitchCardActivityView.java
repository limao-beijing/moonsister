package com.moonsister.tcjy.my.view;

import com.hickey.network.bean.CardInfoBean;
import com.moonsister.tcjy.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/7/4.
 */
public interface SwitchCardActivityView extends BaseIView {
    void setCardInfos(List<CardInfoBean.DataBean> datas);
}
