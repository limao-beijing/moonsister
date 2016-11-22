package com.moonsister.tcjy.find.model;

import com.hickey.network.bean.RankBean;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by jb on 2016/8/3.
 */
public interface RankFragmentModel extends BaseIModel {
    void loadData(int type, int page, onLoadListDateListener<RankBean.DataBean> listener);
}
