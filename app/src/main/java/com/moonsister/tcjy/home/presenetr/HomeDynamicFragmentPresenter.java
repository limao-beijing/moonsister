package com.moonsister.tcjy.home.presenetr;

import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.home.view.HomeDynamicView;

/**
 * Created by jb on 2016/11/29.
 */
public interface HomeDynamicFragmentPresenter extends BaseIPresenter<HomeDynamicView> {
    void loadInitData(int page, String type);
}
