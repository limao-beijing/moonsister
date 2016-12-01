package com.moonsister.tcjy.dialogFragment.model;

import com.hickey.tool.base.BaseIModel;

/**
 * Created by jb on 2016/11/30.
 */
public interface InterestDialogModel extends BaseIModel {
    void loadInitData(onLoadDateSingleListener<Object> listener);

    void submitService(String content, onLoadDateSingleListener<Object> listener);
}
