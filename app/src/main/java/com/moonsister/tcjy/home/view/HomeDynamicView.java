package com.moonsister.tcjy.home.view;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/11/29.
 */
public interface HomeDynamicView extends BaseIView {
    void setInitData(List<DynamicItemBean> bean);
}
