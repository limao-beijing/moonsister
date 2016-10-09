package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.engagement.view.EngagemengRecommendView;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/9/27.
 */
public interface EengegamentRecommendPresenter  extends BaseIPresenter<EngagemengRecommendView>{
    void loadData(int page, EnumConstant.EngegamentType type);
}
