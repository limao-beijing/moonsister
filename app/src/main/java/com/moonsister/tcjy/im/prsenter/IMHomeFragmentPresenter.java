package com.moonsister.tcjy.im.prsenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.im.view.IMHomeView;

/**
 * Created by jb on 2016/6/20.
 */
public interface IMHomeFragmentPresenter extends BaseIPresenter<IMHomeView>{
    void switchNavigation(int id);
}
