package com.moonsister.tcjy.engagement.view;


import com.hickey.network.bean.EngagementManagerBean;
import com.hickey.tool.base.BaseIView;

/**
 * Created by jb on 2016/9/28.
 */
public interface EngagementManagerFragmentView extends BaseIView {
    void setData(EngagementManagerBean bean);

    void submitSuccess(String id);


    void showDelectDialog(EngagementManagerBean.DataBean bean, int position);

}
