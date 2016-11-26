package com.moonsister.tcjy.home.presenetr;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.home.view.SearchResultActivityView;

/**
 * Created by jb on 2016/7/10.
 */
public interface SearchResultActivityPresenter extends BaseIPresenter<SearchResultActivityView> {
    void loadBasicData(String key);
}
