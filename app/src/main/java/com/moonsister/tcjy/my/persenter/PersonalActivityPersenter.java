package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.PersonalActivityFenView;
import com.moonsister.tcjy.my.view.PersonalActivityView;

/**
 * Created by x on 2016/9/2.
 */
public interface PersonalActivityPersenter extends BaseIPresenter<PersonalActivityFenView> {
    void sendPersonalMessageFen(String uid,String get_source);

}
