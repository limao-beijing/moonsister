package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.engagement.view.EngegamentAppealView;

/**
 * Created by jb on 2016/9/29.
 */
public interface EngegamentAppealPresenter extends BaseIPresenter<EngegamentAppealView> {
    void submitAppeal(String id, String content);
}
