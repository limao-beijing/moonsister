package com.moonsister.tcjy.home.model;

import com.hickey.network.bean.KeyMateBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/8/28.
 */
public interface SearchFragmentModel extends BaseIModel {
    void loadKeyMate(String key, onLoadDateSingleListener<KeyMateBean> searchFragmentPersenter);
}
