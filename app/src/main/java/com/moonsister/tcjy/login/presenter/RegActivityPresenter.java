package com.moonsister.tcjy.login.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.login.view.RegThridActivityView;

/**
 * Created by x on 2016/8/31.
 */
public interface RegActivityPresenter extends BaseIPresenter<RegThridActivityView> {
    void getThrid(String mobile, String pwd, String birthday, String code );
    void getSecurityCode(String mobile);
}
