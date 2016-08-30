package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.BuyVipFragmentView;

/**
 * Created by jb on 2016/8/13.
 */
public interface BuyVipFragmentPersenter extends BaseIPresenter<BuyVipFragmentView> {
    void buyVIP(int number, String phone);
}
