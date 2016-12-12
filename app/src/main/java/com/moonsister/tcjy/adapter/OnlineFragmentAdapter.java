package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.HomeThreeFragmentBean;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.viewholder.OnlineFragmentAdapterViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/9/24.
 */
public class OnlineFragmentAdapter extends BaseRecyclerViewAdapter<HomeThreeFragmentBean.DataBean> {
    OnlineFragmentAdapterViewHolder viewHolder;


    public OnlineFragmentAdapter(List<HomeThreeFragmentBean.DataBean> list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        viewHolder = new OnlineFragmentAdapterViewHolder(UIUtils.inflateLayout(R.layout.item_online));
        return viewHolder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {

        return viewHolder;
    }
}
