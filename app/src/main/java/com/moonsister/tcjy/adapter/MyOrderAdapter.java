package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.MyOrderViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/6/27.
 */
public class MyOrderAdapter extends BaseRecyclerViewAdapter {
    private MyOrderViewHolder viewHolder;

    public MyOrderAdapter(List list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        View view = UIUtils.inflateLayout(R.layout.item_order, parent);
        viewHolder = new MyOrderViewHolder(view);
        return viewHolder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return viewHolder;
    }
}
