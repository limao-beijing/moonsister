package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.UserInfoChangeBean;

/**
 * Created by jb on 2016/7/11.
 */
public interface UserinfoActivityModel extends BaseIModel {
    void loadBasicData(String uid, onLoadDateSingleListener<UserInfoChangeBean> listener);
}
