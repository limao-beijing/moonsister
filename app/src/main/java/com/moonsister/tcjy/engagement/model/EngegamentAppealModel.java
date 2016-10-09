package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.StatusBean;

/**
 * Created by jb on 2016/9/29.
 */
public interface EngegamentAppealModel  extends BaseIModel{
    void submitAppeal(String id, String content, onLoadDateSingleListener<StatusBean> listenter);
}
