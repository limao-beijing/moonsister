package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.GetMoneyView;

/**
 * Created by jb on 2016/7/3.
 */
public interface GetMoneyPersenter  extends BaseIPresenter<GetMoneyView>{
    void loadbasicInfo();

    void PaySubmit(String number, int money);
}
