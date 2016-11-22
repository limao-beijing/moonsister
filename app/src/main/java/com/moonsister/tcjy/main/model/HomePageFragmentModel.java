package com.moonsister.tcjy.main.model;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.network.bean.UserInfoDetailBean;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.base.BaseIModel;

import java.util.List;

/**
 * Created by jb on 2016/9/1.
 */
public interface HomePageFragmentModel extends BaseIModel {
    void loadDynamicData(String userId, int page, EnumConstant.SearchType type, onLoadDateSingleListener<List<DynamicItemBean>> listener);

    void loadheaderData(String id, onLoadDateSingleListener<UserInfoDetailBean> listener);
}
