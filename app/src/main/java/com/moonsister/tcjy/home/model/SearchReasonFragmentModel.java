package com.moonsister.tcjy.home.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DynamicBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/8/28.
 */
public interface SearchReasonFragmentModel extends BaseIModel {
    void loadSearchReason(String key, EnumConstant.SearchType type, int page, onLoadDateSingleListener<DynamicBean> listener);

}
