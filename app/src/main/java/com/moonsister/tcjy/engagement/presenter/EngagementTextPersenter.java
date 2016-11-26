package com.moonsister.tcjy.engagement.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.engagement.view.EngagementTextView;

/**
 * Created by jb on 2016/11/11.
 */
public interface EngagementTextPersenter extends BaseIPresenter<EngagementTextView> {
    void loadText(String datingID, EnumConstant.EngegamentTextType type);
}
