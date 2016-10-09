package com.moonsister.tcjy.main.widget;

import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.JsResult;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.js.JavaScriptObject;
import com.moonsister.tcjy.main.presenter.BuyVipFragmentPersenter;
import com.moonsister.tcjy.main.presenter.BuyVipFragmentPersenterImpl;
import com.moonsister.tcjy.main.view.BuyVipFragmentView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.widget.WebView;

/**
 * Created by jb on 2016/9/30.
 */
public class H5Activity extends BaseActivity implements WebView.onWebViewListener, BuyVipFragmentView {
    private WebView mWebView;
    private BuyVipFragmentPersenter persenter;

    @Override
    protected View setRootContentView() {
         mWebView = new WebView(this);
        return mWebView;
    }

    @Override
    protected void initView() {
        persenter = new BuyVipFragmentPersenterImpl();
        persenter.attachView(this);
        //设置本地调用对象及其接口
        mWebView.addJavascriptInterface(new JavaScriptObject(this), "obj");
        mWebView.setWebViewListener(this);
        String url = getIntent().getStringExtra("url");
        if (StringUtis.isEmpty(url)) {
            finish();
            return;
        }
        mWebView.loadUrl(url);
    }


    public static Fragment newInstance() {
        return new BuyVipWebViewFragment();
    }

    @Override
    public void onJsAlert(android.webkit.WebView view, String url, String message, JsResult result) {
        showToast(message);
    }

    @Override
    public void onReceivedTitle(String title) {
        setTitleName(title);
    }

    @Override
    public void onPageStart() {
//        showProgressDialog();
    }

    @Override
    public void onPageFinished() {
//        hideProgressDialog();
    }

    @Override
    public void buySuccess() {
        RxBus.getInstance().send(Events.EventEnum.BUY_VIP_SUCCESS, null);
        PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
        memoryPersonInfoDetail.setVipStatus(1);
        if (StringUtis.equals(AppConstant.CHANNEL_ID, "1015") || StringUtis.equals(AppConstant.CHANNEL_ID, "1009")) {
            memoryPersonInfoDetail.setAttestation(1);
        }
        UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
        finish();
    }

    @Override
    public void typePay(int type, String phone) {
        persenter.buyVIP(type, phone);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }


}
