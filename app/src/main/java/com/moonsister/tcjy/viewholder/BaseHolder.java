package com.moonsister.tcjy.viewholder;


import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseHolder<T> {
    protected View contentView;

    public BaseHolder() {
        contentView = initView();
        ButterKnife.bind(this,contentView);
//        contentView.setTag(this);
    }

    /**
     * 初始化每个控件
     *
     * @return
     */
    protected abstract View initView();

    /**
     * 给每个控件赋值
     *
     * @param data
     */
    public abstract void refreshView(T data);

    /**
     * 获取布局
     *
     * @return
     */
    public View getContentView() {
        return contentView;
    }


}
