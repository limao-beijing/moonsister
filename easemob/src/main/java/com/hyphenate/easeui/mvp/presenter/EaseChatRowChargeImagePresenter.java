package com.hyphenate.easeui.mvp.presenter;

import com.hickey.tool.base.BaseIPresenter;
import com.hyphenate.easeui.mvp.view.EaseChatRowChargeImageView;

/**
 * Created by jb on 2016/11/25.
 */

public interface EaseChatRowChargeImagePresenter extends BaseIPresenter<EaseChatRowChargeImageView> {
    void getImagePic(String lid, String acthcode);
}
