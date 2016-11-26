package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.NearbyBean;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.viewholder.NearbyViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/8/4.
 */
public class NearbyAdapter extends BaseRecyclerViewAdapter<NearbyBean.DataBean> {

    private NearbyViewHolder holder;

    public NearbyAdapter(List<NearbyBean.DataBean> list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        holder = new NearbyViewHolder(UIUtils.inflateLayout(R.layout.item_nearby));
        return holder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return holder;
    }
}
