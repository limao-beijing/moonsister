package com.moonsister.tcjy.home.presenetr;


import com.hickey.network.bean.HomeParams;
import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.home.view.HomeThreeFragmentView;

/**
 * Created by jb on 2016/9/25.
 */
public interface HomeThreeFragmentPresenter extends BaseIPresenter<HomeThreeFragmentView> {
    void laodRefresh(int page, String type, int flag, HomeParams params);

    void loadMore(int page, String type, int flag, HomeParams params);

    void loadBannerData();
}
