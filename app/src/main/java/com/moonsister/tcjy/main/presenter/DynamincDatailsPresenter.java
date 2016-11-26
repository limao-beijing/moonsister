package com.moonsister.tcjy.main.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.DynamicDatailsView;

/**
 * Created by pc on 2016/6/8.
 */
public interface DynamincDatailsPresenter extends BaseIPresenter<DynamicDatailsView> {

    void loadCommentListData(String commentID);

    void sendComment(String id, String content, String pid);

    void deleteDynamic(String id);

    void loadSingeDyamic(String latest_id);
}
