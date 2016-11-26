package com.moonsister.tcjy.engagement.view;

import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengRecommendView extends BaseIView {
    void setRecommend(List<EngagemengRecommendBean.DataBean> data);
}
