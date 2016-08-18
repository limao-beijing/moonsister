package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.RankBean;
import com.moonsister.tcjy.find.view.RankFragmentView;
import com.moonsister.tcjy.find.widget.RankFragment;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.RankViewHolder;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/3.
 */
public class RankAdapter extends BaseRecyclerViewAdapter<RankBean.DataBean> {

    private RankViewHolder holder;
    private RankFragmentView rankFragmentView;

    public RankAdapter(List<RankBean.DataBean> list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        holder = new RankViewHolder(UIUtils.inflateLayout(R.layout.item_rand, parent));
        if (rankFragmentView!=null)
            holder.setRankFragmentView(rankFragmentView);
        return holder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return holder;
    }

    public void setRankFragmentView(RankFragmentView rankFragmentView) {
        this.rankFragmentView = rankFragmentView;
    }
}
