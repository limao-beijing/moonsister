package com.moonsister.tcjy.main.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.ChangepwdActivityView;

/**
 * Created by jb on 2016/7/11.
 */
public interface ChangepwdActivityPresenter extends BaseIPresenter<ChangepwdActivityView> {

    void submit(String oldpwd, String newpwd);
}
