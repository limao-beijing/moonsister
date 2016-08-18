package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DefaultDataBean;

/**
 * Created by jb on 2016/7/11.
 */
public interface ChangepwdActivityModel extends BaseIModel {
    void loadBasic(String oldpwd, String newpwd, onLoadDateSingleListener<DefaultDataBean> listener);
}
