package com.moonsister.tcjy.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.bean.BaseDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/6/4.
 */
public abstract class BaseRecyclerViewAdapter<T extends BaseDataBean> extends RecyclerView.Adapter {
    protected List<T> datas;
//    private int layoutID;

    public BaseRecyclerViewAdapter(List<T> list) {
        this.datas = list;
//        this.layoutID = layoutID;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = initRootView(parent, viewType);
        return getBaseViewHolder(v, viewType);
    }

    protected abstract View initRootView(ViewGroup parent, int viewType);

    protected abstract BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType);


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseRecyclerViewHolder) {
            BaseRecyclerViewHolder bvh = (BaseRecyclerViewHolder) holder;
            if (datas != null) {
                T t = datas.get(position);
                if (t != null) {
                    bvh.setBaseRecyclerViewAdapter(this);
                    bvh.onBindData(t);
                    bvh.onBindData(t, position);
                    bvh.initViewClik(t, position);
                    bvh.setOnClick(t, position);
                }
            }
            ((BaseRecyclerViewHolder) holder).setOnItemClickListener(new BaseRecyclerViewHolder.onItemClickListener() {
                @Override
                public void onItemclick(View view, int position) {
                    BaseRecyclerViewAdapter.this.onItemclick(view, position);
                    if (listener != null)
                        listener.onItemclick(view, position);
                }
            });
        }


    }

    /**
     * 条目点击事件
     *
     * @param view
     * @param position
     */
    public void onItemclick(View view, int position) {

    }

    private onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public void clean() {
        if (datas != null)
            datas.clear();
    }

    /**
     * 置顶条目
     *
     * @param position
     */
    public void stickItme(int position) {
        if (datas != null && datas.size() >= position) {
            T t = datas.get(position);
            datas.remove(t);
            datas.add(0, t);
            notifyDataSetChanged();
        }
    }

    public interface onItemClickListener {
        void onItemclick(View view, int position);
    }

    @Override
    public int getItemCount() {

        return datas == null ? 0 : datas.size();
    }

    /**
     * 添加集合
     *
     * @param list
     */
    public void addListData(List<T> list) {
        if (datas == null)
            datas = new ArrayList<T>();
        datas.addAll(list);
    }

    /**
     * 添加单个数据
     *
     * @param t
     */
    public void addSingleDate(T t) {
        if (datas == null)
            datas = new ArrayList<T>();
        datas.add(t);
    }

    public void delectSingleItme(int position) {
        if (datas != null && datas.size() - 1 >= position) {
            datas.remove(datas.get(position));
            notifyDataSetChanged();
        }

    }

    /**
     * 在某个角标添加单个数据
     *
     * @param index
     * @param t
     */
    public void addSingeData(int index, T t) {
        if (datas == null)
            datas = new ArrayList<T>();
        datas.add(index, t);
    }

    public void onRefresh() {
        notifyDataSetChanged();
    }

}
