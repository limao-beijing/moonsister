package com.moonsister.tcjy.js;


import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.hickey.tool.lang.StringUtis;
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
        String url = getIntent().getStringExtra("url");
        if (StringUtis.isEmpty(url)) {
            finish();
            return;
        }
        mWebView.setWebViewListener(this);
        mWebView.loadUrl(url);

    }

    @Override
    public void onJsAlert(android.webkit.WebView view, String url, String message, JsResult result) {
        showToast(message);
    }

    @Override
    public void onReceivedTitle(String title) {
        if (title == null)
            return;

        setTitleName(title.length() > 8 ? title.substring(0, 8) + "..." : title);
    }

    @Override
    public void onPageStart() {
        showProgressDialog();
    }

    @Override
    public void onPageFinished() {
        hideProgressDialog();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
