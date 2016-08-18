package com.moonsister.tcjy.find.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.find.view.NearbyActivityView;

/**
 * Created by jb on 2016/8/4.
 */
public interface NearbyActivityPresenter extends BaseIPresenter<NearbyActivityView> {
    void refresh(String sex);

    void loadMore(String sex);
}
