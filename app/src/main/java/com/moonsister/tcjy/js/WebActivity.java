package com.moonsister.tcjy.js;


import android.view.View;
import android.webkit.JsResult;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.WebView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/8.
 */
public class WebActivity extends BaseActivity implements WebView.onWebViewListener {
    @Bind(R.id.web_view)
    WebView mWebView;

    @Override
    protected View setRootContentView() {

        return UIUtils.inflateLayout(R.layout.activity_webview);
    }

    @Override
    protected void initView() {


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
        showProgressDialog();
    }

    @Override
    public void onPageFinished() {
        hideProgressDialog();
    }
}
