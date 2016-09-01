package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;

import java.util.List;

/**
 * Created by jb on 2016/9/1.
 */
public interface HomePageFragmentModel extends BaseIModel {
    void loadDynamicData(String userId, int page, onLoadDateSingleListener<List<DynamicItemBean>> listener);

    void loadheaderData(String id, onLoadDateSingleListener<UserInfoDetailBean> listener);
}
