package com.moonsister.tcjy.main.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.main.view.HomePageFragmentView;

/**
 * Created by jb on 2016/9/1.
 */
public interface HomePageFragmentPresenter extends BaseIPresenter<HomePageFragmentView> {
    void loadRefresh(String userId, EnumConstant.SearchType type);

    void loadHeader(String userId);

    void loadMore(String userId, EnumConstant.SearchType type);
}
