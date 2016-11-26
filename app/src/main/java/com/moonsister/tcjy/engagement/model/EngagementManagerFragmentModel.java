package com.moonsister.tcjy.engagement.model;


import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;

/**
 * Created by jb on 2016/9/28.
 */
public interface EngagementManagerFragmentModel extends BaseIModel {
    void loadData(EnumConstant.ManagerType type, int page, onLoadDateSingleListener listener);

//    void submitSuccess(String id, onLoadDateSingleListener listener);
//
//    void submitInviteSuccess(String message, String type, onLoadDateSingleListener listener);
}
