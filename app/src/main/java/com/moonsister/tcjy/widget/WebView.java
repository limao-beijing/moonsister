package com.moonsister.tcjy.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.moonsister.tcjy.manager.FileDownManger;
import com.moonsister.tcjy.utils.LogUtils;

/**
 * Created by jb on 2016/9/8.
 */
public class WebView extends android.webkit.WebView {

    private onWebViewListener listener;

    public WebView(Context context) {
        super(context);
        initView();
    }

    public WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 监听事件
     */
    public interface onWebViewListener {
        void onJsAlert(android.webkit.WebView view, String url, String message, JsResult result);

        void onReceivedTitle(String title);

        void onPageStart();

        void onPageFinished();
    }

    /**
     * 设置监听事件
     *
     * @param listener
     */
    public void setWebViewListener(onWebViewListener listener) {
        this.listener = listener;
    }

    private void initView() {
        //设置支持javascript脚本
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //设置编码
        webSettings.setDefaultTextEncodingName("utf-8");
        //支持js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);

        webSettings.setLoadWithOverviewMode(true);


        webSettings.setGeolocationEnabled(true);

        webSettings.setDomStorageEnabled(true);

        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        setWebChromeClient(new MyWebChromeClient());
        setWebViewClient(new MyWebViewClient());
        this.setDownloadListener(new MyWebViewDownLoadListener());
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            LogUtils.i("tag", "url=" + url);
            LogUtils.i("tag", "userAgent=" + userAgent);
            LogUtils.i("tag", "contentDisposition=" + contentDisposition);
            LogUtils.i("tag", "mimetype=" + mimetype);
            LogUtils.i("tag", "contentLength=" + contentLength);
            new FileDownManger().downApk(getContext(), url);
//            Uri uri = Uri.parse(url);
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
        }
    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(android.webkit.WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (listener != null) {
                listener.onReceivedTitle(title);
            }
        }

        @Override
        public boolean onJsAlert(android.webkit.WebView view, String url, String message, JsResult result) {
            if (listener != null) {
                listener.onJsAlert(view, url, message, result);
                result.cancel();
                return true;
            }
            return super.onJsAlert(view, url, message, result);
        }
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
            // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
            LogUtils.d(this, "url : " + url);
            loadUrl(url);
            // 消耗掉这个事件。Android中返回True的即到此为止吧,事件就会不会冒泡传递了，我们称之为消耗掉
            return true;
        }

        @Override
        public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            LogUtils.d(this, "url : " + url);
            if (listener != null)
                listener.onPageStart();
        }

        @Override
        public void onPageFinished(android.webkit.WebView view, String url) {
            super.onPageFinished(view, url);
            LogUtils.d(this, "url : " + url);
            if (listener != null)
                listener.onPageFinished();
        }
    }

}
