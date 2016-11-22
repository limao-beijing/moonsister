package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.engagement.view.EngagemengRecommendView;
import com.hickey.tool.constant.EnumConstant;

/**
 * Created by jb on 2016/9/27.
 */
public interface EengegamentRecommendPresenter  extends BaseIPresenter<EngagemengRecommendView>{
    void loadData(String userType, int page, EnumConstant.EngegamentType type);
}
