package com.moonsister.tcjy.home.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.DynamicBean;

import java.util.List;

/**
 * Created by jb on 2016/8/28.
 */
public interface SearchReasonFragmentView  extends BaseIView{


    void setLoadResult(List<DynamicBean.DataBean.ListBean> data);
}
