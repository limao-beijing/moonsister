package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.UserInfoChangeBean;

/**
 * Created by jb on 2016/7/11.
 */
public interface UserInfoChangeActivityModel extends BaseIModel {
    void loadBasiData(onLoadDateSingleListener listener);

    void submit(UserInfoChangeBean.DataBean dataBean, onLoadDateSingleListener listener);
}
