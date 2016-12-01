package com.moonsister.tcjy.home.model;

import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.base.BaseIModel;

import java.util.List;

/**
 * Created by jb on 2016/11/29.
 */
public interface HomePersonFragmentModel extends BaseIModel{
    void loadInitData(int page, String type, onLoadDateSingleListener<List<EngagemengRecommendBean.DataBean>> listenter);
}
