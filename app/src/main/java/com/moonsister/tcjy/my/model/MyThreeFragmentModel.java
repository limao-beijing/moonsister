package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;

/**
 * Created by jb on 2016/9/25.
 */
public interface MyThreeFragmentModel extends BaseIModel {
    void loadData(String uid, int page, String type, onLoadDateSingleListener<BaseBean> listener);

    void loadHeaderData(String uid, onLoadDateSingleListener<BaseBean> listener);

    void deleteRes(String id, onLoadDateSingleListener<BaseBean> listener);
}
