package com.moonsister.tcjy.dialogFragment.presenter;

import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.dialogFragment.view.InterestDialogView;

/**
 * Created by jb on 2016/11/30.
 */
public interface InterestDialogPresenter extends BaseIPresenter<InterestDialogView> {
    void loadInitData();

    void submitService(String content);
}
