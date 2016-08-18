package com.moonsister.tcjy.my.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.GetMoneyBean;

/**
 * Created by jb on 2016/7/3.
 */
public interface GetMoneyView extends BaseIView {
    void setBasicInfo(GetMoneyBean getMoneyBean);

    void pageFinish();
}
