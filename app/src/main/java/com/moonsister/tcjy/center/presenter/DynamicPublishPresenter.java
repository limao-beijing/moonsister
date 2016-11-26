package com.moonsister.tcjy.center.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.center.view.DefaultDynamicView;

import java.util.List;

/**
 * Created by jb on 2016/6/23.
 */
public interface DynamicPublishPresenter extends BaseIPresenter<DefaultDynamicView> {
    void sendDynamic(EnumConstant.DynamicType dynamicType, String content, List<String> datas, String tags, String address);

}
