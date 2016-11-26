package com.moonsister.tcjy.login.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.login.view.RegiterFragmentView;

/**
 * Created by pc on 2016/6/14.
 */
public interface RegiterFragmentPresener extends BaseIPresenter<RegiterFragmentView> {
    void getSecurityCode(String phoneNumber);

    void submitRegiter(String phoneNumber,String code);
}
