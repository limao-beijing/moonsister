package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.home.view.SearchReasonFragmentView;
import com.hickey.tool.constant.EnumConstant;

/**
 * Created by jb on 2016/8/28.
 */
public interface SearchReasonFragmentPersenter extends BaseIPresenter<SearchReasonFragmentView> {
    void loadSearchReason(String key, boolean isLoadMore, EnumConstant.SearchType type);
}
