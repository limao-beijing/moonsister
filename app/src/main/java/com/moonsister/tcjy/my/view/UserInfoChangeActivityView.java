package com.moonsister.tcjy.my.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.UserInfoChangeBean;

/**
 * Created by jb on 2016/7/11.
 */
public interface UserInfoChangeActivityView extends BaseIView {
    void setUserBasic(UserInfoChangeBean userInfoChangeBean);

    void pageFinish();
}
