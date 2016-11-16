package com.moonsister.tcjy.engagement.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.EngagementManagerBean;

/**
 * Created by jb on 2016/9/28.
 */
public interface EngagementManagerFragmentView extends BaseIView {
    void setData(EngagementManagerBean bean);

    void submitSuccess(String id);


    void showDelectDialog(EngagementManagerBean.DataBean bean, int position);

}
