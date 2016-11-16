package com.moonsister.tcjy.base;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.moonsister.tcjy.bean.BaseDataBean;

import butterknife.ButterKnife;


/**
 * Created by pc on 2016/6/4.
 */
public abstract class BaseRecyclerViewHolder<T extends BaseDataBean> extends RecyclerView.ViewHolder {
    private View mRootView;
    protected BaseRecyclerViewAdapter<T> baseRecyclerViewAdapter;
    public BaseIView baseIView;


    public BaseRecyclerViewHolder(View view) {
        super(view);
        this.mRootView = view;
        ButterKnife.bind(this, view);
    }

    /**
     * 获取字符串资源
     *
     * @param id
     * @return
     */
    public String getString(@StringRes int id) {
        return mRootView.getContext().getResources().getString(id);
    }

    /**
     * 获取图片资源
     *
     * @param id
     * @return
     */
    public Drawable getDrawable(@DrawableRes int id) {
        return mRootView.getContext().getResources().getDrawable(id);
    }

    /**
     * 获取颜色资源
     *
     * @param id
     * @return
     */
    public int getColor(@ColorRes int id) {
        return mRootView.getContext().getResources().getColor(id);
    }


    public void setView(BaseIView view) {
        this.baseIView = view;
    }

    /**
     * 设置点击事件
     *
     * @param t
     * @param position
     */
    public void setOnClick(final T t, final int position) {
        if (mRootView != null) {
            mRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemclick(v, t, position);
                    if (listener != null)
                        listener.onItemclick(v, position);
                }
            });
            mRootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return BaseRecyclerViewHolder.this.onLongClick(v, t, position);
                }
            });
        }

    }

    /**
     * 得到根布局
     *
     * @return
     */
    public View getRootView() {
        return mRootView;
    }

    public abstract void onBindData(T t);

    public void onBindData(T t, int position) {
    }

    /**
     * 初始布局监听事件
     */
    public void initViewClik(T t, int position) {

    }

    /**
     * @param view     点击的view
     * @param t        点击的对象数据
     * @param position 点击位置
     */
    protected abstract void onItemclick(View view, T t, int position);

    /**
     * 长按点击事件
     *
     * @param view
     * @param t
     * @param position
     */
    protected boolean onLongClick(View view, T t, int position) {
        return false;
    }

    private onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public void setBaseRecyclerViewAdapter(BaseRecyclerViewAdapter<T> baseRecyclerViewAdapter) {
        this.baseRecyclerViewAdapter = baseRecyclerViewAdapter;
    }


    public interface onItemClickListener {
        void onItemclick(View view, int position);
    }

}
