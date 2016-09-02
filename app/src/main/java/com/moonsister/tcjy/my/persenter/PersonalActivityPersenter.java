package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.PersonalActivityView;

/**
 * Created by x on 2016/9/2.
 */
public interface PersonalActivityPersenter extends BaseIPresenter<PersonalActivityView> {
    void sendPersonalMessage(int uid);
}
