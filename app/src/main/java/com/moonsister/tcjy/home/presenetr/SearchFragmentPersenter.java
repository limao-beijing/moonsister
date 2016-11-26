package com.moonsister.tcjy.home.presenetr;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.home.view.SearchFragmentView;

/**
 * Created by jb on 2016/8/28.
 */
public interface SearchFragmentPersenter extends BaseIPresenter<SearchFragmentView> {
    void loadKeyMate(String key);

}
