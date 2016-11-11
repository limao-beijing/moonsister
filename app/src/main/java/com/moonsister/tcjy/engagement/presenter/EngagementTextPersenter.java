package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.engagement.view.EngagementTextView;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/11/11.
 */
public interface EngagementTextPersenter extends BaseIPresenter<EngagementTextView> {
    void loadText(String datingID, EnumConstant.EngegamentTextType type);
}
