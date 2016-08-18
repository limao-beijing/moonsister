package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.MainView;

/**
 * Created by pc on 2016/6/1.
 */
public interface MainPresenter extends BaseIPresenter<MainView> {
    void switchNavigation(int id);

    void getRongyunKey();

    void loginRongyun();

    void getCertificationStatus();


    void getUserPermission();

    void getUserFriendList();

}
