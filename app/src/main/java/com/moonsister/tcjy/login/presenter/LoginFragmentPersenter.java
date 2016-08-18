package com.moonsister.tcjy.login.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.login.view.LoginFragmentView;

/**
 * Created by jb on 2016/6/17.
 */
public interface LoginFragmentPersenter extends BaseIPresenter<LoginFragmentView> {
    void loginSubmit(String phone, String password);
}
