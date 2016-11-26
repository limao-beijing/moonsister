package com.moonsister.tcjy.engagement.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.engagement.view.EngagementDetailsView;

/**
 * Created by jb on 2016/11/11.
 */
public interface EngagementDetailsPersenter extends BaseIPresenter<EngagementDetailsView> {
    void loadByIdData(String id);


    void loadByIdData2(String id);

    void loadByOrderIdData2(String id);
}
