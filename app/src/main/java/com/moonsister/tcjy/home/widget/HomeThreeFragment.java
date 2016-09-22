package com.moonsister.tcjy.home.widget;

import android.view.View;

import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.viewholder.HomeThreeHeaderViewHolder;

/**
 * Created by jb on 2016/9/22.
 */
public class HomeThreeFragment extends BaseListFragment<HomeThreeFragmentAdapter> {
    HomeThreeHeaderViewHolder threeHeaderViewHolder;

    @Override
    public HomeThreeFragmentAdapter setAdapter() {
        return new HomeThreeFragmentAdapter(null);
    }

    @Override
    protected View addListViewHead() {
        threeHeaderViewHolder = new HomeThreeHeaderViewHolder();
        threeHeaderViewHolder.setActivity(getActivity());
        return threeHeaderViewHolder.getContentView();
    }

    @Override
    protected void onRefresh() {
        threeHeaderViewHolder.refreshView("");
    }

    @Override
    protected void onLoadMore() {

    }
}
