package com.moonsister.tcjy.engagement.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.engagement.view.EngagemengRecommendView;

/**
 * Created by jb on 2016/9/27.
 */
public interface EengegamentRecommendPresenter  extends BaseIPresenter<EngagemengRecommendView> {
    void loadData(String userType, int page, EnumConstant.EngegamentType type);
}
