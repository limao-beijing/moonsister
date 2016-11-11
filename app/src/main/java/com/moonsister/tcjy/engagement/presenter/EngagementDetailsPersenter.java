package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.engagement.view.EngagementDetailsView;

/**
 * Created by jb on 2016/11/11.
 */
public interface EngagementDetailsPersenter extends BaseIPresenter<EngagementDetailsView> {
    void loadData(String id);
}
