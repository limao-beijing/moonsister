package com.moonsister.tcjy.center.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.VipRule;

/**
 * Created by jb on 2016/6/29.
 */
public interface BuyDynamicRedPackketView extends BaseIView {
    void finishPage();

    void setVipRule(VipRule bean);
}
