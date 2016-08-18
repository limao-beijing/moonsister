package com.moonsister.tcjy.home.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.SearchReasonBaen;

/**
 * Created by jb on 2016/7/10.
 */
public interface SearchResultActivityModel extends BaseIModel{
    void loadBasicData(String key, int page, onLoadDateSingleListener<SearchReasonBaen> listener);
}
