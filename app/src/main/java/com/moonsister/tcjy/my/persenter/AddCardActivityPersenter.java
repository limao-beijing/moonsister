package com.moonsister.tcjy.my.persenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.AddCardActivityView;

/**
 * Created by jb on 2016/7/3.
 */
public interface AddCardActivityPersenter extends BaseIPresenter<AddCardActivityView> {
    void submitAccount(String cardNumber, String username, String cardType, String bankname);
}
