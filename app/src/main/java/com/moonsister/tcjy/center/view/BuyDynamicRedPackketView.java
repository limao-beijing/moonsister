package com.moonsister.tcjy.center.view;

import com.hickey.network.bean.VipRule;
import com.hickey.tool.base.BaseIView;


/**
 * Created by jb on 2016/6/29.
 */
public interface BuyDynamicRedPackketView extends BaseIView {
    void finishPage();

    void setVipRule(VipRule bean);
}
