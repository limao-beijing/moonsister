package com.hyphenate.easeui.mvp.model;

import android.content.Context;

import com.hickey.network.bean.resposen.BaseModel;
import com.hickey.tool.base.BaseIModel;

import java.util.List;

/**
 * Created by jb on 2016/11/26.
 */
public interface ChargeMessageActivityModel extends BaseIModel {
    void submitData(Context context, boolean checked, String money, List<String> contents, final String desc, int type, String uid, long duration, String authcode, onLoadDateSingleListener<BaseModel> listenter);

    void loadInitData(String authcode, onLoadDateSingleListener<BaseModel> listener);
}
