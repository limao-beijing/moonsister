package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.UserinfoActivityView;

/**
 * Created by jb on 2016/7/11.
 */
public interface UserinfoActivityPresenter  extends BaseIPresenter<UserinfoActivityView>{
    void loadBasicData(String uid);
}
