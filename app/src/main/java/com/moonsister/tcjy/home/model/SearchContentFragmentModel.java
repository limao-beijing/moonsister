package com.moonsister.tcjy.home.model;

import com.hickey.network.bean.ChooseKeyBean;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by jb on 2016/8/28.
 */
public interface SearchContentFragmentModel extends BaseIModel {
    void loadChooseKey(onLoadDateSingleListener<ChooseKeyBean> listener);
}
