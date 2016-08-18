package com.moonsister.tcjy.im.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.FrientBaen;

/**
 * Created by jb on 2016/7/8.
 */
public interface FrientFragmentModel extends BaseIModel {
    void loadBasicData(int pageType, int page ,onLoadDateSingleListener<FrientBaen> listener);
}
