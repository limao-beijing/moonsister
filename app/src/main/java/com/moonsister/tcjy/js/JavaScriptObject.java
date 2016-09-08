package com.moonsister.tcjy.js;

import android.webkit.JavascriptInterface;

import com.moonsister.tcjy.main.view.BuyVipFragmentView;

/**
 * Created by jb on 2016/9/8.
 */
public class JavaScriptObject {
    private final BuyVipFragmentView mView;

    public JavaScriptObject(BuyVipFragmentView mView) {
        this.mView = mView;
    }

    @JavascriptInterface
    public void typePay(int payType, String phone) {
        mView.typePay(payType, phone);
    }
}
