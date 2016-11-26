package com.moonsister.tcjy.my.view;

import com.hickey.network.bean.GetMoneyBean;
import com.hickey.tool.base.BaseIView;


/**
 * Created by jb on 2016/7/3.
 */
public interface GetMoneyView extends BaseIView {
    void setBasicInfo(GetMoneyBean getMoneyBean);

    void pageFinish();
}
