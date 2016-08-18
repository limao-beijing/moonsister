package com.moonsister.tcjy.center.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.center.view.DefaultDynamicView;
import com.moonsister.tcjy.utils.EnumConstant;

import java.util.List;

/**
 * Created by jb on 2016/6/23.
 */
public interface DynamicPublishPresenter extends BaseIPresenter<DefaultDynamicView> {
    void sendDynamic(EnumConstant.DynamicType dynamicType, String content, List<String> datas, String address);
}
