package com.moonsister.tcjy.my.persenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.WithdRawDepositView;

/**
 * Created by jb on 2016/7/2.
 */
public interface WithdRawDepositPresenter extends BaseIPresenter<WithdRawDepositView> {
    void loadEnableMoney();
}
