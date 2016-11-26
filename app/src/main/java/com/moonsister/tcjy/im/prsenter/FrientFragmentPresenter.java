package com.moonsister.tcjy.im.prsenter;

import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.FrientFragmentView;

/**
 * Created by jb on 2016/7/8.
 */
public interface FrientFragmentPresenter extends BaseIPresenter<FrientFragmentView> {
    void loadBasicData(int pageType);

    void loadRefresh(int pageType);
}
