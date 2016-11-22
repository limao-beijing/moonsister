package com.moonsister.tcjy.main.model;

import com.hickey.network.bean.FrientBaen;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by jb on 2016/7/22.
 */
public interface RelationActivityModel extends BaseIModel {
    void loadData(int type, int page, String uid, onLoadDateSingleListener<FrientBaen> listener);
//    void toup(int type, String to_uid, onLoadDateSingleListener<BaseBean> listener);
}
