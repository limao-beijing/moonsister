package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.BalanceActivityView;

/**
 * Created by x on 2016/9/2.
 */
public interface MoneyActivityPersenter extends BaseIPresenter<BalanceActivityView> {
    void moneyba(int type,int page,int pagesize);
}
