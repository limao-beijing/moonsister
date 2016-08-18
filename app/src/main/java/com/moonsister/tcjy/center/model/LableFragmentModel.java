package com.moonsister.tcjy.center.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.LableBean;

/**
 * Created by jb on 2016/8/10.
 */
public interface LableFragmentModel extends BaseIModel {
    void loadData(int page, onLoadDateSingleListener<LableBean> listener);
}
