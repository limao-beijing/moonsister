package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.home.view.SearchReasonFragmentView;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/8/28.
 */
public interface SearchReasonFragmentPersenter extends BaseIPresenter<SearchReasonFragmentView> {
    void loadSearchReason(String key, EnumConstant.SearchType type);
}
