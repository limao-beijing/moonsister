package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.FrientBaen;
import com.moonsister.tcjy.bean.PingbiBean;
import com.moonsister.tcjy.main.presenter.RelationActivityPresenterImpl;

/**
 * Created by jb on 2016/7/22.
 */
public interface RelationActivityModel extends BaseIModel {
    void loadData(int type, int page, String uid, onLoadDateSingleListener<FrientBaen> listener);
    void toup(int type, String to_uid, onLoadDateSingleListener<BaseBean> listener);
}
