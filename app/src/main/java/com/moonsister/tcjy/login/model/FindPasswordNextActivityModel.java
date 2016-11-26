package com.moonsister.tcjy.login.model;


import com.hickey.tool.base.BaseIModel;

/**
 * Created by jb on 2016/7/11.
 */
public interface FindPasswordNextActivityModel extends BaseIModel {
    void submit(String newpwd, String code, onLoadDateSingleListener listenter);
}
