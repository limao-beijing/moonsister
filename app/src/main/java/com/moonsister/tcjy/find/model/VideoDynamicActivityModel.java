package com.moonsister.tcjy.find.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.UserInfoListBean;

/**
 * Created by jb on 2016/9/14.
 */
public interface VideoDynamicActivityModel extends BaseIModel {
    void loadData(int page, onLoadDateSingleListener<UserInfoListBean> listener);
}
