package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.bean.UserInfoChangeBean;
import com.moonsister.tcjy.my.view.UserInfoChangeActivityView;

/**
 * Created by jb on 2016/7/11.
 */
public interface UserInfoChangeActivityPresenter extends BaseIPresenter<UserInfoChangeActivityView> {

    void loadbasicData();

    void submit(UserInfoChangeBean.DataBean dataBean);
}
