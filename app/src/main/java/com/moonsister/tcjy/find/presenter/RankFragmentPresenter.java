package com.moonsister.tcjy.find.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.find.view.RankFragmentView;

/**
 * Created by jb on 2016/8/3.
 */
public interface RankFragmentPresenter extends BaseIPresenter<RankFragmentView> {

    void refresh(int type);

    void loadMore(int type);
}
