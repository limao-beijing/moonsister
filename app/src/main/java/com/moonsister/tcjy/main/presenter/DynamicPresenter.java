package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.DynamicView;

/**
 * Created by pc on 2016/6/6.
 */
public interface DynamicPresenter extends BaseIPresenter<DynamicView> {
    void loadonRefreshData(String userId);

    void loadLoadMoreData(String userId);

    void switchNavigation(int id);

    void loadUserInfodetail(String uid);

    void deleteDynamic(String id);

    void upDynamic(String id);

    void delUpDynamic(String id);
}
