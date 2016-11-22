package com.moonsister.tcjy.find.view;


import com.hickey.network.bean.DynamicItemBean;
import com.moonsister.tcjy.main.view.BasePageFragmentView;

import java.util.List;

/**
 * Created by jb on 2016/9/14.
 */
public interface VideoDynamicActivityView extends BasePageFragmentView {
    void setVideoDynamic(List<DynamicItemBean> list);
}
