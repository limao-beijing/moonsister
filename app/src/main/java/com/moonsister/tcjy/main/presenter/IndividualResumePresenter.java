package com.moonsister.tcjy.main.presenter;

import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.IndividualResumeView;

/**
 * Created by jb on 2016/12/1.
 */
public interface IndividualResumePresenter extends BaseIPresenter<IndividualResumeView> {
    void loadInitData(String uid);
}
