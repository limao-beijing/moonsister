package com.moonsister.tcjy.im.model;

import com.hickey.network.bean.FrientBaen;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/7/8.
 */
public interface FrientFragmentModel extends BaseIModel {
    void loadBasicData(int pageType, int page ,onLoadDateSingleListener<FrientBaen> listener);
}
