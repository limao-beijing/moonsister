package com.moonsister.tcjy.home.view;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/8/28.
 */
public interface SearchReasonFragmentView  extends BaseIView {


    void setLoadResult(List<DynamicItemBean> data);
}
