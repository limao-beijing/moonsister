package com.hyphenate.easeui.mvp.presenter;

import com.hickey.tool.base.BaseIPresenter;
import com.hyphenate.easeui.mvp.view.ChargeMessageActivityView;

import java.util.List;

/**
 * Created by jb on 2016/11/26.
 */
public interface ChargeMessageActivityPresenter extends BaseIPresenter<ChargeMessageActivityView> {
    void submitData(String money, List<String> contents, String desc, int type, String uid, long duration, String authcode);

}
