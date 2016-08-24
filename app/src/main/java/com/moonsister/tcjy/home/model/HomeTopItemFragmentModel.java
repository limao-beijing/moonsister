package com.moonsister.tcjy.home.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.HomeTopItemBean;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/8/24.
 */
public interface HomeTopItemFragmentModel  extends BaseIModel{
    void loadData(int page, String tagId, EnumConstant.HomeTopFragmentTop homeType, onLoadDateSingleListener<HomeTopItemBean> listener);
}
