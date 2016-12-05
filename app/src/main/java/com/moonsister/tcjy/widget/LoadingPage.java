package com.moonsister.tcjy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.moonsister.tcjy.R;
import com.hickey.tool.widget.UIUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public abstract class LoadingPage<T> extends FrameLayout {
    // 创建Fragment的界面
    // 五种 状态 默认 加载中 加载失败, 加载成功 , 加载为空
    public static final int STATE_NONE = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;

    public int state = STATE_NONE; // 记录当前的状态 根据状态不同 显示不同的界面

    public LoadingPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadingPage(Context context) {
        super(context);
        // 1. 添加4个界面
        initView();
    }

    private View loadingView;// 加载中的界面
    private View errorView;// 错误界面
    private View emptyView;// 空的界面
    private View successView;// 成功的界面

    /*
     * // 1. 添加3个界面
     */
    private void initView() {
        loadingView = createLoadingView();
        this.addView(loadingView);// 给帧布局添加了加载中的界面
        errorView = createErrorView();
        this.addView(errorView);// 添加错误界面
        emptyView = createEmptyView();
        this.addView(emptyView);// 添加空的界面
        // 2 然后根据状态不同 控件界面的显示和隐藏
        showPage();
    }

    /*
     * 2 然后根据状态不同 控件界面的显示和隐藏
     */
    private void showPage() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_LOADING
                    || state == STATE_NONE ? View.VISIBLE : View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (state == STATE_SUCCESS) {
            if (successView == null) {
                successView = createSuccessView();
                this.addView(successView);
            }
            successView.invalidate();
            successView.setVisibility(View.VISIBLE);
        }

    }

    // 创建空的界面
    private View createEmptyView() {
        return UIUtils.inflateLayout(R.layout.loadpage_empty);
    }

    // 创建错误界面
    private View createErrorView() {
        View view = UIUtils.inflateLayout(R.layout.loadpage_error);
        Button page_bt = (Button) view.findViewById(R.id.page_bt);
        page_bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    /**
     * 3 请求服务器 修改状态
     */
    public void show() {
        if (state == STATE_ERROR || state == STATE_EMPTY) {
            state = STATE_NONE;
        }

        if (state == STATE_NONE) {
            state = STATE_LOADING;
            // 创建了新的线程 复用android 自带的容器
            // new Thread().showTopBanner(); // 而应该对线程进行复用
            // 线程池的概念
            Observable.create(new Observable.OnSubscribe<LoadResult>() {
                @Override
                public void call(Subscriber<? super LoadResult> subscriber) {
                    LoadResult result = onLoad();
                    subscriber.onNext(result);
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<LoadResult>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable throwable) {

                        }

                        @Override
                        public void onNext(LoadResult loadResult) {
                            state = loadResult.getValue();
                            showPage();// 根据状态 修改界面
                        }
                    });
        }
        showPage();

    }


    public enum LoadResult {
        error(2), empty(3), success(4);

        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    // 创建加载中的界面
    private View createLoadingView() {
        return UIUtils.inflateLayout(R.layout.loadpage_loading);
    }

    /**
     * 创建成功界面
     *
     * @return
     */
    protected abstract View createSuccessView();

    /**
     * 请求服务器
     *
     * @return
     */
    public abstract LoadResult onLoad();

}
