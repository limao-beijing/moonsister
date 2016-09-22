package com.moonsister.tcjy.home.widget;

import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.base.BaseRecyclerViewAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.HomeThreeFragmentBean;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public class HomeThreeFragmentAdapter extends BaseRecyclerViewAdapter<HomeThreeFragmentBean> {
    public HomeThreeFragmentAdapter(List<HomeThreeFragmentBean> list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return null;
    }
}
