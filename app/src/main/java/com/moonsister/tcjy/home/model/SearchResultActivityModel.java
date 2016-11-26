package com.moonsister.tcjy.home.model;

import com.hickey.network.bean.SearchReasonBaen;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/7/10.
 */
public interface SearchResultActivityModel extends BaseIModel {
    void loadBasicData(String key, int page, onLoadDateSingleListener<SearchReasonBaen> listener);
}
