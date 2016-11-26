package com.hyphenate.easeui.mvp.presenter;

import android.app.Activity;

import com.hickey.tool.base.BaseIPresenter;
import com.hickey.tool.constant.EnumConstant;
import com.hyphenate.easeui.mvp.view.ChargeMessageDialogView;

/**
 * Created by jb on 2016/11/26.
 */
public interface ChargeMessageDialogPresenter extends BaseIPresenter<ChargeMessageDialogView> {
    void pay(Activity activity, EnumConstant.PayType type, String lid, String acthcode);
}
