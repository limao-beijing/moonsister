package com.moonsister.tcjy.home.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.HomeTopItemBean;

import java.util.List;

/**
 * Created by jb on 2016/8/24.
 */
public interface HomeTopItemFragmentView extends BaseIView {
    void setData(List<HomeTopItemBean.DataBean> data);
}
