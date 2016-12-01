package com.moonsister.tcjy.home.view;

import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/11/29.
 */
public interface HomePersonFragmentView  extends BaseIView{
    void setInitData(List<EngagemengRecommendBean.DataBean> been);
}
