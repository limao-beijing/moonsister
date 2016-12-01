package com.moonsister.tcjy.home.presenetr;

import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.home.view.HomePersonFragmentView;

/**
 * Created by jb on 2016/11/29.
 */
public interface HomePersonFragmentPresenter extends BaseIPresenter<HomePersonFragmentView>{
    void loadInitData(int page, String type);
}
