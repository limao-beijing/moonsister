package com.moonsister.tcjy.home.presenetr;


import com.hickey.tool.base.BaseIPresenter;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.home.view.SearchReasonFragmentView;

/**
 * Created by jb on 2016/8/28.
 */
public interface SearchReasonFragmentPersenter extends BaseIPresenter<SearchReasonFragmentView> {
    void loadSearchReason(String key, boolean isLoadMore, EnumConstant.SearchType type);
}
