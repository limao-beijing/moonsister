package com.moonsister.tcjy.main.model;

import com.hickey.network.bean.DefaultDataBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/7/11.
 */
public interface ChangepwdActivityModel extends BaseIModel {
    void loadBasic(String oldpwd, String newpwd, onLoadDateSingleListener<DefaultDataBean> listener);
}
