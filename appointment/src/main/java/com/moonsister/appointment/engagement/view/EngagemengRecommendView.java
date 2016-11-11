package com.moonsister.appointment.engagement.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.EngagemengRecommendBean;

import java.util.List;

/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengRecommendView extends BaseIView {
    void setRecommend(List<EngagemengRecommendBean.DataBean> data);
}
