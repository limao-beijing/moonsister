package com.moonsister.tcjy.my.persenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.RechargeActivityView;

/**
 * Created by x on 2016/9/3.
 */
public interface RechargeActivityPersenter extends BaseIPresenter<RechargeActivityView> {
    void LoadData(String money,String pay_type);
}
