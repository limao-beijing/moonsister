package com.moonsister.tcjy.home.view;

import com.hickey.network.bean.HomeTopItemBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/8/24.
 */
public interface HomeTopItemFragmentView extends BaseIView {
    void setData(List<HomeTopItemBean.DataBean> data);
}
