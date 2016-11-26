package com.moonsister.tcjy.home.presenetr;


import com.hickey.tool.base.BaseIPresenter;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.home.view.HomeTopItemFragmentView;

/**
 * Created by jb on 2016/8/24.
 */
public interface HomeTopItemFragmentPersenter  extends BaseIPresenter<HomeTopItemFragmentView> {
    void loadRefresh(boolean isRefresh, String tagId, EnumConstant.HomeTopFragmentTop homeType);
}
