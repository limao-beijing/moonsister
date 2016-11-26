package com.moonsister.tcjy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.GoodSelectBaen;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.viewholder.GoodSelectViewHolder;

import java.util.List;

/**
 * Created by pc on 2016/6/4.
 */
public class GoodSelectAdapter extends BaseRecyclerViewAdapter<GoodSelectBaen.Data> {



    public GoodSelectAdapter(List list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_good_select, parent, false);
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return new GoodSelectViewHolder(v);
    }
}
