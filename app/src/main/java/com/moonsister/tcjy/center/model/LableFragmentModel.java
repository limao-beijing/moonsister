package com.moonsister.tcjy.center.model;

import com.hickey.network.bean.LableBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/8/10.
 */
public interface LableFragmentModel extends BaseIModel {
    void loadData(int page, onLoadDateSingleListener<LableBean> listener);
}
