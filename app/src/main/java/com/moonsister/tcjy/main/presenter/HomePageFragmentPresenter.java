package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.HomePageFragmentView;
import com.hickey.tool.constant.EnumConstant;

/**
 * Created by jb on 2016/9/1.
 */
public interface HomePageFragmentPresenter extends BaseIPresenter<HomePageFragmentView> {
    void loadRefresh(String userId, EnumConstant.SearchType type);

    void loadHeader(String userId);

    void loadMore(String userId, EnumConstant.SearchType type);
}
