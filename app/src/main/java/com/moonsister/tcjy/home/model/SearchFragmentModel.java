package com.moonsister.tcjy.home.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.KeyMateBean;
import com.moonsister.tcjy.home.presenetr.SearchFragmentPersenterImpl;

/**
 * Created by jb on 2016/8/28.
 */
public interface SearchFragmentModel extends BaseIModel{
    void loadKeyMate(String key, onLoadDateSingleListener<KeyMateBean> searchFragmentPersenter);
}
