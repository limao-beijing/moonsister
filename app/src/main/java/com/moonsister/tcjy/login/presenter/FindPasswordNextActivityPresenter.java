package com.moonsister.tcjy.login.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.login.view.FindPasswordNextActivityView;

/**
 * Created by jb on 2016/7/11.
 */
public interface FindPasswordNextActivityPresenter extends BaseIPresenter<FindPasswordNextActivityView> {
    void submit(String newpwd, String code);
}
