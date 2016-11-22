package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.home.view.HomeTopItemFragmentView;
import com.hickey.tool.constant.EnumConstant;

/**
 * Created by jb on 2016/8/24.
 */
public interface HomeTopItemFragmentPersenter  extends BaseIPresenter<HomeTopItemFragmentView>{
    void loadRefresh(boolean isRefresh, String tagId, EnumConstant.HomeTopFragmentTop homeType);
}
