package com.moonsister.tcjy.main.model;


import com.hickey.tool.base.BaseIModel;

/**
 * Created by jb on 2016/7/1.
 */
public interface MainActivityModel extends BaseIModel {
    void getRongyunKey(onLoadDateSingleListener listener);

    void getCertificationStatus(onLoadDateSingleListener listener);

    void getUserPermission(onLoadDateSingleListener listener);

    void getUserFriendList(onLoadDateSingleListener listener);
}
