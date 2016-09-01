package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.HomePageFragmentView;

/**
 * Created by jb on 2016/9/1.
 */
public interface HomePageFragmentPresenter  extends BaseIPresenter<HomePageFragmentView>{
    void loadRefresh(String userId);

    void loadHeader(String userId);

    void loadMore(String userId);
}
