package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.EngagementManagerBean;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.engagement.view.EngagementManagerFragmentView;
import com.moonsister.tcjy.viewholder.EngagementManagerViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/9/28.
 */
public class EngagementManagerAdapter extends BaseRecyclerViewAdapter<EngagementManagerBean.DataBean> {


    private EngagementManagerViewHolder viewHolder;

    public EngagementManagerAdapter(List<EngagementManagerBean.DataBean> list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        viewHolder = new EngagementManagerViewHolder(UIUtils.inflateLayout(R.layout.vh_engagement_manager));

        return viewHolder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        viewHolder.setView(mBaseIView);
        return viewHolder;
    }

    public void setBaseView(EngagementManagerFragmentView baseView) {
        mBaseIView = baseView;
    }
}
