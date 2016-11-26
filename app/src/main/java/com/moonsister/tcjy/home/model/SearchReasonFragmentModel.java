package com.moonsister.tcjy.home.model;

import com.hickey.network.bean.DynamicBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;


/**
 * Created by jb on 2016/8/28.
 */
public interface SearchReasonFragmentModel extends BaseIModel {
    void loadSearchReason(String key, EnumConstant.SearchType type, int page, onLoadDateSingleListener<DynamicBean> listener);

}
