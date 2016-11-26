package com.hyphenate.easeui.mvp.model;

import static com.hickey.network.ModuleServerApi.getAppAPI;


/**
 * Created by jb on 2016/11/25.
 */
public class EaseChatRowChargeImageModelImpl implements EaseChatRowChargeImageModel {
    @Override
    public void getImagePic(String lid, String acthcode, final onLoadDateSingleListener listenter) {
        ObservableUtis.$(getAppAPI().getChargeMessage(lid, acthcode), DataType.DATA_ZERO, listenter);
    }
}
