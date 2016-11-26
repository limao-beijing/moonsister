package com.moonsister.tcjy.my.model;

import com.hickey.network.bean.UserInfoChangeBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/7/11.
 */
public interface UserInfoChangeActivityModel extends BaseIModel {
    void loadBasiData(onLoadDateSingleListener listener);

    void submit(UserInfoChangeBean.DataBean dataBean, onLoadDateSingleListener listener);
}
