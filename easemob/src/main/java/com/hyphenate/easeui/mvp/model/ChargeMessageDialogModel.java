package com.hyphenate.easeui.mvp.model;

import android.app.Activity;

import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;

/**
 * Created by jb on 2016/11/26.
 */
public interface ChargeMessageDialogModel extends BaseIModel {
    void pay(Activity activity, EnumConstant.PayType type, String lid, String acthcode, onLoadDateSingleListener<Long> listener);
}
