package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.HomeTopItemBean;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.HomeTopItemFragmentViewHolder;
import com.moonsister.tcjy.widget.RoundedImageView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/24.
 */
public class HomeTopItemFragmentAdapter extends BaseRecyclerViewAdapter<HomeTopItemBean.DataBean> {


    public HomeTopItemFragmentAdapter(List<HomeTopItemBean.DataBean> list) {
        super(list);
    }

    private BaseRecyclerViewHolder<HomeTopItemBean.DataBean> viewHolder;

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                viewHolder = new HomeTopItemFragmentViewHolder(UIUtils.inflateLayout(R.layout.item_home_top_1));
                break;
        }
        return viewHolder == null ? null : viewHolder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return viewHolder;
    }
}
