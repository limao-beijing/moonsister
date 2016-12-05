package com.moonsister.tcjy.main.model;

import com.hickey.network.bean.resposen.IndividualResumeBean;
import com.hickey.tool.base.BaseIModel;

/**
 * Created by jb on 2016/12/1.
 */
public interface IndividualResumeModel extends BaseIModel {
    void loadInitData(String uid, onLoadDateSingleListener<IndividualResumeBean> listenter);
}
