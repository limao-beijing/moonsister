package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.engagement.view.EngagementManagerFragmentView;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/9/28.
 */
public interface EngagementManagerFragmentPresenter extends BaseIPresenter<EngagementManagerFragmentView> {
    void loadData(EnumConstant.ManagerType type, int page);

    void submitSuccess(String message);

    void submitInviteSuccess(String message, String type);
}
