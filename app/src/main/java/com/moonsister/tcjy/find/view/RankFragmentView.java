package com.moonsister.tcjy.find.view;

import com.hickey.network.bean.RankBean;
import com.moonsister.tcjy.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/8/3.
 */
public interface RankFragmentView extends BaseIView {
    void setData(List<RankBean.DataBean> t);
}
