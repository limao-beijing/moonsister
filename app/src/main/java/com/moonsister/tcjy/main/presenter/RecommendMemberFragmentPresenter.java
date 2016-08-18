package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.RecommendMemberFragmentView;

/**
 * Created by jb on 2016/8/15.
 */
public interface RecommendMemberFragmentPresenter extends BaseIPresenter<RecommendMemberFragmentView> {
    void loadData();

}
