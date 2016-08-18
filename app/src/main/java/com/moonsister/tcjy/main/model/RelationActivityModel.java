package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.FrientBaen;

/**
 * Created by jb on 2016/7/22.
 */
public interface RelationActivityModel extends BaseIModel {
    void loadData(int type, int page, String uid, onLoadDateSingleListener<FrientBaen> listener);
}
