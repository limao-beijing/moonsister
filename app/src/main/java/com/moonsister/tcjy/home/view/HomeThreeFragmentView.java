package com.moonsister.tcjy.home.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.HomeThreeFragmentBean;

import java.util.List;

/**
 * Created by jb on 2016/9/25.
 */
public interface HomeThreeFragmentView extends BaseIView {
    void setData(List<HomeThreeFragmentBean.DataBean> data);

}
