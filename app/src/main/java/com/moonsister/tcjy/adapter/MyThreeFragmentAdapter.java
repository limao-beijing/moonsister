package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.MyThreeFragmentBean;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.viewholder.MyThreeFragmentViewHoder;

import java.util.List;


/**
 * Created by jb on 2016/9/22.
 */
public class MyThreeFragmentAdapter extends BaseRecyclerViewAdapter<MyThreeFragmentBean.DataBean> {


    public MyThreeFragmentAdapter(List<MyThreeFragmentBean.DataBean> list) {
        super(list);
    }

    private MyThreeFragmentViewHoder viewHoder;

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        viewHoder = new MyThreeFragmentViewHoder(UIUtils.inflateLayout(R.layout.item_my_three_fragment));
        return viewHoder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return viewHoder;
    }
}
