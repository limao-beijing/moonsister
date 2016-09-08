package com.moonsister.tcjy.main.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.js.JavaScriptObject;
import com.moonsister.tcjy.main.presenter.BuyVipFragmentPersenter;
import com.moonsister.tcjy.main.presenter.BuyVipFragmentPersenterImpl;
import com.moonsister.tcjy.main.view.BuyVipFragmentView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.widget.WebView;

/**
 * Created by jb on 2016/9/8.
 */
public class BuyVipViewViewFragment extends BaseFragment implements WebView.onWebViewListener, BuyVipFragmentView {
    private WebView mWebView;
    private BuyVipFragmentPersenter persenter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        persenter = new BuyVipFragmentPersenterImpl();
        persenter.attachView(this);
        mWebView = new WebView(container.getContext());
        return mWebView;
    }

    @Override
    protected void initData() {
        //设置本地调用对象及其接口
        mWebView.addJavascriptInterface(new JavaScriptObject(this), "obj");
        mWebView.setWebViewListener(this);
        mWebView.loadUrl("http://2test.yytbzs.cn:88/indextest.php/index/mmvip/info/?authcode=" + UserInfoManager.getInstance().getAuthcode() + "&channel=" + AppConstant.CHANNEL_ID);
    }

    public static Fragment newInstance() {
        return new BuyVipViewViewFragment();
    }

    @Override
    public void onJsAlert(android.webkit.WebView view, String url, String message, JsResult result) {
        showToast(message);
    }

    @Override
    public void onReceivedTitle(String title) {
        ((BaseActivity) getActivity()).setTitleName(title);
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
        UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
        getActivity().finish();
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
