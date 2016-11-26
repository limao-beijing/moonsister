package com.moonsister.tcjy.home.model;

import com.hickey.network.bean.HomeTopItemBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;


/**
 * Created by jb on 2016/8/24.
 */
public interface HomeTopItemFragmentModel  extends BaseIModel {
    void loadData(int page, String tagId, EnumConstant.HomeTopFragmentTop homeType, onLoadDateSingleListener<HomeTopItemBean> listener);
}
