package com.hyphenate.easeui.mvp.model;

import com.hickey.network.bean.resposen.ChargeResBean;
import com.hickey.tool.base.BaseIModel;

/**
 * Created by jb on 2016/11/25.
 */
public interface EaseChatRowChargeImageModel extends BaseIModel {
    void getImagePic(String lid, String acthcode, onLoadDateSingleListener<ChargeResBean> listenter);
}
