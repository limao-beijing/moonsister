package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.CommentDataListBean;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.viewholder.DynamicCommentViewHolder;

import java.util.List;

/**
 * Created by pc on 2016/6/8.
 */
public class DynamicDetailsAdapter extends BaseRecyclerViewAdapter<CommentDataListBean.DataBean> {


    public DynamicDetailsAdapter(List<CommentDataListBean.DataBean> list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        return UIUtils.inflateLayout(R.layout.item_comment);
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return new DynamicCommentViewHolder(v);
    }
}
