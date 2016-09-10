package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.PersonalReviseActivityView;

/**
 * Created by x on 2016/9/10.
 */
public interface PersonalReviseActivityPersenter extends BaseIPresenter<PersonalReviseActivityView> {
    void sendPersonalReviseMessage(int uid);
}
