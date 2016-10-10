package com.moonsister.tcjy.main.widget;

import android.view.View;
import android.webkit.JsResult;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.WebView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/30.
 */
public class H5Activity extends BaseActivity implements WebView.onWebViewListener {
    @Bind(R.id.wv)
    WebView mWebView;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_h5);
    }

    @Override
    protected void initView() {

        //设置本地调用对象及其接口
        mWebView.setWebViewListener(this);
        String url = getIntent().getStringExtra("url");
        if (StringUtis.isEmpty(url)) {
            finish();
            return;
        }
        mWebView.loadUrl(url);
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

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
