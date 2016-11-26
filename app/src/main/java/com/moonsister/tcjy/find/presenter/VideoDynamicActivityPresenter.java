package com.moonsister.tcjy.find.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.find.view.VideoDynamicActivityView;

/**
 * Created by jb on 2016/9/14.
 */
public interface VideoDynamicActivityPresenter extends BaseIPresenter<VideoDynamicActivityView> {
    void loadRefresh();

    void loadMore();
}
