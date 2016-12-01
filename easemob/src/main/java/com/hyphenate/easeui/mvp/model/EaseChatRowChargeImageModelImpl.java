package com.hyphenate.easeui.mvp.model;

import com.hickey.network.bean.resposen.ChargeResBean;

import static com.hickey.network.ModuleServerApi.getAppAPI;


/**
 * Created by jb on 2016/11/25.
 */
public class EaseChatRowChargeImageModelImpl implements EaseChatRowChargeImageModel {
    @Override
    public void getImagePic(String lid, String acthcode, final onLoadDateSingleListener<ChargeResBean> listenter) {
        ObservableMapUtils.$_Map(getAppAPI().getChargeMessage(lid, acthcode), DataType.DATA_ZERO, listenter);
    }
}
