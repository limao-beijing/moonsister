package com.moonsister.tcjy.login.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.FindPasswordActivityView;

/**
 * Created by jb on 2016/7/11.
 */
public interface FindPasswordActivityPresenter extends BaseIPresenter<FindPasswordActivityView> {
    void getSecurityCode(String phoneNumber);

    void submitRegiter(String phone, String code);
}
