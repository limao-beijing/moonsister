package com.moonsister.tcjy.my.persenter;

import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.PersonalReviseActivityView;

/**
 * Created by x on 2016/9/10.
 */
public interface PersonalReviseActivityPersenter extends BaseIPresenter<PersonalReviseActivityView> {
    void sendPersonalReviseMessage(String uid);
    void sendUserJson(String contents);
}
