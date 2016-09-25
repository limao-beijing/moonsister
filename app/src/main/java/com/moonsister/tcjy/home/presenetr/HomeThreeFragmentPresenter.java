package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.bean.HomeParams;
import com.moonsister.tcjy.home.view.HomeThreeFragmentView;

/**
 * Created by jb on 2016/9/25.
 */
public interface HomeThreeFragmentPresenter extends BaseIPresenter<HomeThreeFragmentView> {
    void laodRefresh(int page, String type, HomeParams params);

    void loadMore(int page, String type, HomeParams params);
}
