package com.moonsister.tcjy.home.view;


import com.hickey.network.bean.BannerBean;
import com.hickey.network.bean.HomeThreeFragmentBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/9/25.
 */
public interface HomeThreeFragmentView extends BaseIView {
    void setData(List<HomeThreeFragmentBean.DataBean> data);

    void setBanner(BannerBean bean);
}
