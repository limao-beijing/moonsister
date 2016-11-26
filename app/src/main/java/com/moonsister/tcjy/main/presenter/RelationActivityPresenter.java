package com.moonsister.tcjy.main.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.RelationActivityView;

/**
 * Created by jb on 2016/7/22.
 */
public interface RelationActivityPresenter  extends BaseIPresenter<RelationActivityView> {
    void loadData(int type, String uid);
//    void upup(int type, String to_uid);
}
