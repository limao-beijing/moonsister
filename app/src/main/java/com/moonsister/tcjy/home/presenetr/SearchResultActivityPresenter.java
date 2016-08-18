package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.home.view.SearchResultActivityView;

/**
 * Created by jb on 2016/7/10.
 */
public interface SearchResultActivityPresenter extends BaseIPresenter<SearchResultActivityView>{
    void loadBasicData(String key);
}
