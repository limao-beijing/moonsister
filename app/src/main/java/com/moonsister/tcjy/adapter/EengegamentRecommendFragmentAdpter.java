package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.BaseDataBean;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.viewholder.EengegamentRecommendFragmentViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public class EengegamentRecommendFragmentAdpter extends BaseRecyclerViewAdapter<BaseDataBean> {


    private EengegamentRecommendFragmentViewHolder holder;

    public EengegamentRecommendFragmentAdpter(List<BaseDataBean> list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        holder = new EengegamentRecommendFragmentViewHolder(UIUtils.inflateLayout(R.layout.item_engegament_recommend));
        return holder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return holder;
    }
}
