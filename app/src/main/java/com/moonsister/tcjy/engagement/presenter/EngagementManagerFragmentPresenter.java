package com.moonsister.tcjy.engagement.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.engagement.view.EngagementManagerFragmentView;

/**
 * Created by jb on 2016/9/28.
 */
public interface EngagementManagerFragmentPresenter extends BaseIPresenter<EngagementManagerFragmentView> {
    void loadData(EnumConstant.ManagerType type, int page);
}
