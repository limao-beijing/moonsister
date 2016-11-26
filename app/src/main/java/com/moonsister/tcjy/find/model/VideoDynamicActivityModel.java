package com.moonsister.tcjy.find.model;

import com.hickey.network.bean.UserInfoListBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/9/14.
 */
public interface VideoDynamicActivityModel extends BaseIModel {
    void loadData(int page, onLoadDateSingleListener<UserInfoListBean> listener);
}
