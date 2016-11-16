package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DefaultDataBean;

/**
 * Created by jb on 2016/9/29.
 */
public interface EngegamentAppealModel  extends BaseIModel{
    void submitAppeal(String id, String content, onLoadDateSingleListener<DefaultDataBean> listenter);
}
