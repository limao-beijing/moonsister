package com.moonsister.tcjy.my.view;

import com.hickey.network.bean.UserInfoChangeBean;
import com.moonsister.tcjy.base.BaseIView;


/**
 * Created by jb on 2016/7/11.
 */
public interface UserInfoChangeActivityView extends BaseIView {
    void setUserBasic(UserInfoChangeBean userInfoChangeBean);

    void pageFinish();
}
